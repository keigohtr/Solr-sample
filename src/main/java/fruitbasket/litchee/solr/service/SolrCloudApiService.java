package fruitbasket.litchee.solr.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;

import fruitbasket.litchee.solr.helper.SolrCloudApi;


public class SolrCloudApiService {

  /* Your zookeeper URL */
  private String zkHost="localhost:19080"; /* FIXME set your own setting. */
  /* Your collection name */
  private String solrCollection="litchee"; /* FIXME set your own setting. */

  public List<String> getApiIds(String query, int offset, int limit) {
    SolrQuery squery = new SolrQuery(query)
        .setStart(offset)
        .setRows(limit);
    return SolrCloudApi.searchApis(zkHost, solrCollection, squery);
  }

}