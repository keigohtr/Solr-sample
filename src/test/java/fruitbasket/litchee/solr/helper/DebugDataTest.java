package fruitbasket.litchee.solr.helper;

import java.util.ArrayList;
import java.util.List;

import fruitbasket.litchee.solr.entity.DocApi;
import fruitbasket.litchee.solr.helper.SolrCloudApi;
import junit.framework.TestCase;


public class DebugDataTest extends TestCase {

  public void test() {
    String zkHost = "localhost:19080";/* FIXME set your own zookeeper host. */
    String collection = "litchee";/* FIXME set your own collection name. */

    List<DocApi> list = new ArrayList<DocApi>();
    for (long i=1; i<3; i++) {
      DocApi doc = new DocApi();
      doc.setId(String.valueOf(i));
      doc.setTitle("Hello");
      doc.setDescription("description");
      doc.setCategoryList(new ArrayList<String>(){{add("chk1");}{add("chk2");}});
      doc.setEnabled(true);
      doc.setSolrRegisteredAt(null);
      list.add(doc);
    }
    for (long i=3; i<5; i++) {
      DocApi doc = new DocApi();
      doc.setId(String.valueOf(i));
      doc.setTitle("おはよう");
      doc.setDescription("説明");
      doc.setCategoryList(new ArrayList<String>(){{add("chk2");}{add("chk3");}});
      doc.setEnabled(true);
      doc.setSolrRegisteredAt(null);
      list.add(doc);
    }
    for (long i=100; i<126; i++) {
      DocApi doc = new DocApi();
      doc.setId(String.valueOf(i));
      doc.setTitle("Debug");
      doc.setDescription("description");
      doc.setCategoryList(new ArrayList<String>(){{add("chk3");}{add("chk4");}});
      doc.setEnabled(true);
      doc.setSolrRegisteredAt(null);
      list.add(doc);
    }
    SolrCloudApi.addApis(zkHost, collection, list);
    assertEquals(true,true);
  }
}