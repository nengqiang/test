package com.hnq.test;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/11/15
 */
class TextClassificationTest {

    private static final String COMPLAINT_TRAIN_FOLDER = "src/main/resources/data/train/complaint_type";

    private static final String LABEL_TRAIN_FOLDER = "src/main/resources/data/train/ticket_label";

    private static final String COMPLAINT_TYPE_MODEL_NAME = "complaint-type-model.ser";

    private static final String TICKET_LABEL_MODEL_NAME = "ticket-label-model.ser";


    /**
     *
     *
     * train model
     *
     *
     */


    @Test
    void trainComplaintTypeModel() throws IOException {
        IClassifier classifier = new NaiveBayesClassifier();
        classifier.train(COMPLAINT_TRAIN_FOLDER);
        NaiveBayesModel model = (NaiveBayesModel) classifier.getModel();
        IOUtil.saveObjectTo(model, "src/main/resources/data/model/" + COMPLAINT_TYPE_MODEL_NAME);
    }

    @Test
    void trainTicketLabelModel() throws IOException {
        IClassifier classifier = new NaiveBayesClassifier();
        classifier.train(LABEL_TRAIN_FOLDER);
        NaiveBayesModel model = (NaiveBayesModel) classifier.getModel();
        IOUtil.saveObjectTo(model, "src/main/resources/data/model/" + TICKET_LABEL_MODEL_NAME);
    }

    /**
     *
     *
     *
     * classify test
     *
     *
     *
     */

    @Test
    void complaintTypeClassifyTest() {
        String input = "功夫贷利息远高于国家规定，高利贷提供结清证明,销账,调整利率本人于2018年2月在功夫贷平台上借款32000元，每月需还款2044.39元，24个月还清，即总费用为2044.39*24=49065.36元，年利率大致在46%，与合同中写的完全不符。之前是不太懂这些，一直稀里糊涂的还着款，现在已经还了20期，共计40887.8元，本金已经还清，利息也已经付了将近9000元。现在的诉求是希望能够对剩下的四期进行减免，并销账，出具结清证明。";
        classifyAndOutput(COMPLAINT_TYPE_MODEL_NAME, input);
    }

    @Test
    void complaintTypeClassifyBatchTest() throws IOException {
        String data = FileUtils.readFileToString(new File("src/main/resources/data/origin/test.txt"), Charsets.UTF_8);
        List<String> res = Arrays.stream(data.split("\\s\\n")).collect(Collectors.toList())
                .stream().map(s -> s.replaceAll("\\n", " ")).collect(Collectors.toList());
        res.forEach(re -> classifyAndOutput(COMPLAINT_TYPE_MODEL_NAME, re));
    }

    @Test
    void ticketLabelClassifyTest() {
        String input = "功夫贷高利贷 减掉剩余利息 利息超出国家标准，高利贷，本人2018年2月7日，再功夫贷贷了31000，分24期，谁知道最后要还47749.54元，利息就占了16749.54元，现在已经还了19期已经还了35865.51元，还剩余利息11884.03元。而且他们暴力催收，骚扰我家人。剩下的利息部分请减免。感谢黑猫。";
        classifyAndOutput(TICKET_LABEL_MODEL_NAME, input);
    }

    @Test
    void ticketLabelClassifyBatchTest() throws IOException {
        InputStream ins = this.getClass().getClassLoader().getResourceAsStream("data/origin/test.txt");
        if (ins == null) {
            System.err.println("no data");
            return;
        }
        String data = IOUtils.toString(ins, Charsets.UTF_8);
        List<String> res = Arrays.stream(data.split("\\s\\n")).collect(Collectors.toList())
                .stream().map(s -> s.replaceAll("\\n", " ")).collect(Collectors.toList());
        res.forEach(re -> classifyAndOutput(TICKET_LABEL_MODEL_NAME, re));
    }

    private void classifyAndOutput(String modelName, String input) {
        try {
//        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(modelPath);
            InputStream ins = this.getClass().getClassLoader().getResourceAsStream("data/model/" + modelName);
            NaiveBayesModel model = (NaiveBayesModel) (new ObjectInputStream(ins).readObject());
            IClassifier classifier = new NaiveBayesClassifier(model);
            predict(classifier, input);
            System.out.println();
            Map<String, Double> result = classifier.predict(input)
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, Maps::newLinkedHashMap));
            result.forEach((type, score) -> System.out.println(type + "=" + score));
            System.out.println("---------------------------------------------------");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void predict(IClassifier classifier, String text) {
        System.out.printf("《%s》 \n属于分类 【%s】\n", text, classifier.classify(text));
    }


}
