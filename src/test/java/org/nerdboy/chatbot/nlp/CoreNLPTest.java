package org.nerdboy.chatbot.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.semgraph.SemanticGraph;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.nerdboy.chatbot.nlp.CoreNLP;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class CoreNLPTest {

    public static CoreNLP nlp;

    @BeforeClass
    public static void setUp() throws Exception {
        nlp=new CoreNLP();
    }

    @Ignore
    public void testParticiple() throws Exception {
        List<String> words=nlp.participle("今天天气不错，挺风和日丽的");
        for(String word:words){
            System.out.println(word);
        }
    }

    @Ignore
    public void testTokenize(){
        List<CoreLabel> labeLs=nlp.tokenize("今天的天气不错呀,今天的天气真不错呀，今天的天气那真是相当的不错呀，今天的天气格外的好,今儿个天气好的出奇");
        for(CoreLabel label:labeLs){
            System.out.println("1"+label.getString(CoreAnnotations.TextAnnotation.class));
            System.out.println("2"+label.getString(CoreAnnotations.PartOfSpeechAnnotation.class));
            System.out.println("3"+label.getString(CoreAnnotations.NamedEntityTagAnnotation.class));
        }

    }

    @Ignore
    public void testCorefChain(){
        System.out.println("chain");
        System.out.println(nlp.corefChain("我喜欢你"));
    }

    @Ignore
    public void testCorefMention(){
        System.out.println("mention");
        System.out.println(nlp.corefMention("我喜欢你"));
    }

    @Ignore
    public void testGetGraph(){
        System.out.println("graph");
        System.out.println(nlp.getGraph("今天天气不错，我们出去走走，不出去的话对不起这大好春光啊！"));
        System.out.println(nlp.getGraph("今天天气真晴朗"));
        System.out.println(nlp.getGraph("你猜猜我是谁"));
        System.out.println(nlp.getGraph("真好"));
        System.out.println(nlp.getGraph("你被我爱着"));
        System.out.println(nlp.getGraph("最近过的怎么样"));
    }

    @Ignore
    public void testGetTree(){
        System.out.println("tree");
        System.out.println(nlp.getTree("今天天气不错，我们出去走走，不出去的话对不起这大好春光啊！"));
        System.out.println(nlp.getTree("今天天气真晴朗"));
        System.out.println(nlp.getTree("你猜猜我是谁"));
        System.out.println(nlp.getTree("真好"));
        System.out.println(nlp.getTree("你被我爱着"));
        System.out.println(nlp.getTree("最近过的怎么样"));
    }

    @Ignore
    public void testGetDependency(){
        System.out.println("dependency");
        System.out.println(nlp.getDependency("今天天气不错，我们出去走走，不出去的话对不起这大好春光啊！").toString(SemanticGraph.OutputFormat.LIST));
        System.out.println(nlp.getDependency("今天天气真晴朗").toString(SemanticGraph.OutputFormat.LIST));
        System.out.println(nlp.getDependency("你猜猜我是谁").toString(SemanticGraph.OutputFormat.LIST));
        System.out.println(nlp.getDependency("真好").toString(SemanticGraph.OutputFormat.LIST));
        System.out.println(nlp.getDependency("你被我爱着").toString(SemanticGraph.OutputFormat.LIST));
        System.out.println(nlp.getDependency("最近过的怎么样").toString(SemanticGraph.OutputFormat.LIST));
    }

    @Test
    public void testQuestion(){
        System.out.println("dependency");
        System.out.println(nlp.getGraph("五加四等于几？"));
        System.out.println(nlp.getGraph("五加四是多少？"));
        System.out.println(nlp.getGraph("今天天气怎么样？"));
        System.out.println(nlp.getGraph("天气怎么样？"));
        System.out.println(nlp.getGraph("附近有什么吃的？"));
        System.out.println(nlp.getGraph("五加四等于几？"));
        System.out.println(nlp.getGraph("讲个笑话听听"));
        System.out.println(nlp.getGraph("双子座运势如何？"));
        System.out.println(nlp.getGraph("今天农历多少？"));
        System.out.println(nlp.getGraph("今天有什么段子？"));
        System.out.println(nlp.getGraph("有什么新闻没有？"));
        System.out.println(nlp.getGraph("邯郸学步什么意思？"));
        System.out.println(nlp.getGraph("茻字怎么念？"));
        System.out.println(nlp.getGraph("现在几点了？"));
        System.out.println(nlp.getGraph("几点了"));
        System.out.println(nlp.getGraph("杭州天气"));
        System.out.println(nlp.getGraph("(5+4)*2-1"));
    }
}