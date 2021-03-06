package fruitbasket.litchee.solr.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fruitbasket.litchee.solr.entity.DocApi;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;


public class SolrCloudApi {

  private static final Logger LOG = LoggerFactory.getLogger(SolrCloudApi.class);


  /**
   * Optimization.
   *
   * @param solrUrl
   */
  public static void optimize(String zkHost, String collection) {
    LOG.info("Optimize solr index start.");
    try {
      CloudSolrClient server = new CloudSolrClient(zkHost);
      server.setDefaultCollection(collection);
      server.optimize();
      server.close();
    } catch (SolrServerException e) {
      LOG.error("SolrServerException",e);
    } catch (IOException e) {
      LOG.error("IOException",e);
    }
    LOG.info("Optimize solr index end.");
  }

  /**
   * Insert solr index.
   *
   * @param solrUrl
   * @param list
   */
  public static void addApis(String zkHost, String collection, List<DocApi> list){
    LOG.info("Update solr index start.");
    try {
      CloudSolrClient server = new CloudSolrClient(zkHost);
      server.setDefaultCollection(collection);
      Collection<SolrInputDocument> sdocs = new ArrayList<SolrInputDocument>();
      for (DocApi doc: list) {
        SolrInputDocument sdoc = new SolrInputDocument();
        sdoc.addField("id", doc.getId());
        sdoc.addField("title", doc.getTitle());
        sdoc.addField("description", doc.getDescription());
        sdoc.addField("categoryList", doc.getCategoryList().toArray());
        sdoc.addField("enabled", doc.getEnabled());
        sdoc.addField("solrRegisteredAt", new Date());
        sdocs.add(sdoc);
      }
      server.add( sdocs );
      server.commit();
      server.close();
    } catch (SolrServerException e) {
      LOG.error("SolrServerException",e);
    } catch (IOException e) {
      LOG.error("IOException",e);
    }
    LOG.info("Update solr index end.");
  }

  /**
   * Search.
   *
   * @param solrUrl
   * @param query
   * @return
   */
  public static List<String> searchApis(String zkHost, String collection, SolrQuery query) {
    LOG.info("Search solr start.");
    List<String> apiIds = new ArrayList<String>();
    try {
      CloudSolrClient server = new CloudSolrClient(zkHost);
      server.setDefaultCollection(collection);
      QueryResponse response = server.query(query);
      SolrDocumentList list = response.getResults();
      LOG.debug(list.getNumFound() + " hits.");
      for (SolrDocument doc : list) {
        apiIds.add((String)doc.get("id"));
      }
      server.close();
    } catch (SolrServerException e) {
      LOG.error("SolrServerException",e);
    } catch (IOException e) {
      LOG.error("IOException",e);
    }
    LOG.info("Search solr end.");
    return apiIds;
  }

}
