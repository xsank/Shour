package org.nerdboy.chatbot.nlp;

import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.hcoref.data.Mention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class CoreNLP {

    public static final String NLP_PROPERTY = "StanfordCoreNLP-chinese.properties";
    public static Set<String> FilterTypes = new HashSet<String>() {{
        add("DEG");
        add("SP");
        add("PU");
        add("AD");
        add("DEV");
        add("M");
        add("VA");
    }};

    public static StanfordCoreNLP nlp;

    static {
        nlp = new StanfordCoreNLP(NLP_PROPERTY);
    }

    public static Annotation makeAnnotation(String sentence) {
        Annotation annotation = new Annotation(sentence);
        nlp.annotate(annotation);
        return annotation;
    }

    public static List<CoreMap> sentence(String sentence) {
        Annotation annotation = makeAnnotation(sentence);
        return annotation.get(CoreAnnotations.SentencesAnnotation.class);
    }

    public static Collection<CorefChain> corefChain(String sentence) {
        Annotation annotation = makeAnnotation(sentence);
        return annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class).values();
    }

    public static CoreMap getOneSentence(String sentence) {
        List<CoreMap> sentences = sentence(sentence);
        return sentences.get(0);
    }

    public static List<Mention> corefMention(String sentence) {
        List<Mention> mentions = new ArrayList<Mention>();
        for (CoreMap coreMap : sentence(sentence)) {
            mentions.addAll(coreMap.get(CorefCoreAnnotations.CorefMentionsAnnotation.class));
        }
        return mentions;
    }

    public static List<CoreLabel> tokenize(String sentence) {
        return getOneSentence(sentence).get(CoreAnnotations.TokensAnnotation.class);
    }

    public static List<String> words(String sentence) {
        return tokensToStrings(tokenize(sentence));
    }

    public static List<String> filterWords(String sentence) {
        return tokensToStrings(filterTokenize(sentence));
    }

    public static List<CoreLabel> filterTokenize(String sentence) {
        List<CoreLabel> tokens = tokenize(sentence);
        tokens = filter(tokens);
        return tokens;
    }

    public static Tree getTree(String sentence) {
        return getOneSentence(sentence).get(TreeCoreAnnotations.TreeAnnotation.class);
    }

    public static SemanticGraph getGraph(String sentence) {
        return getOneSentence(sentence).get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
    }

    public static SemanticGraph getDependency(String sentence) {
        return getOneSentence(sentence).get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class);
    }

    public static List<String> participle(String sentence) {
        List<String> words = new ArrayList<String>();
        List<CoreLabel> coreLabels = tokenize(sentence);
        for (CoreLabel label : coreLabels) {
            words.add(getWord(label));
        }
        return words;
    }

    public static List<String> tokensToStrings(List<CoreLabel> tokens) {
        List<String> words = new ArrayList<String>();
        for (CoreLabel label : tokens) {
            words.add(getWord(label));
        }
        return words;
    }

    public static String getPOS(CoreLabel label) {
        return label.getString(CoreAnnotations.PartOfSpeechAnnotation.class);
    }

    public static List<CoreLabel> filter(List<CoreLabel> tokens) {
        Iterator<CoreLabel> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            String type = CoreNLP.getPOS(iterator.next());
            if (FilterTypes.contains(type)) {
                iterator.remove();
            }
        }
        return tokens;
    }

    public static String getWord(CoreLabel label) {
        return label.getString(CoreAnnotations.TextAnnotation.class);
    }

    public static String getNET(CoreLabel label) {
        return label.getString(CoreAnnotations.NamedEntityTagAnnotation.class);
    }
}
