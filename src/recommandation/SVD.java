package recommandation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.ParallelSGDFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import static recommandation.FXMLDocumentController.sliderFx11;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static recommandation.FXMLDocumentController.*;

/**
 * Created by hichem Bedjaoui on 2017-05-14.
 */
public class SVD {
    long  d;
    public static double result11;
    long start=System.currentTimeMillis();
    public static double Mae;
    public static double Rmse;
    public static double score11;
    public  static ObservableList<dataInfo> data = FXCollections.observableArrayList();
    public ObservableList<dataInfo> getItem(){
        return data;
    }

    public double getEval(){
        return result11;
    }
    public double getrmsEval(){
        return score11;
    }
    public double getTime(){
        return d;
    }


    public static String typeFactorizer=ComboboxAlgoFx11.getValue();
    public void test() throws IOException, TasteException{
        DataModel model = new FileDataModel(new File(pathh));





        ALSWRFactorizer factorizer = new ALSWRFactorizer(model, 50, 0.065, 15);
        ParallelSGDFactorizer factorizer2 = new ParallelSGDFactorizer(model,10,0.1,1);
        SVDPlusPlusFactorizer factorizer3 = new SVDPlusPlusFactorizer(model,10,10);
        SVDRecommender recommender;

             recommender = new SVDRecommender(model, factorizer);

        if(typeFactorizer == "ParallelSGDFactorizer")
             recommender = new SVDRecommender(model, factorizer2);

        else if(typeFactorizer == "SVDPlusPlusFactorizer")
            recommender = new SVDRecommender(model, factorizer3);

        // GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model,similarity);


        if(ComboBoxAlgFx11.getValue()=="Tout"){
            int Nbr=Integer.valueOf(nbrRecomFx11.getText());

            for (int i = 1; i <944; i++) {


                List<RecommendedItem> recommendations = recommender.recommend(i,Nbr);
                for (RecommendedItem recommendation : recommendations) {

                    System.out.print("recommendation:");
                    System.out.println(recommendation);
                    data.add(new dataInfo(i+"", recommendation.getItemID()+"", recommendation.getValue()+""));
                }
            }

        }
        int Nbr=Integer.valueOf(nbrRecomFx11.getText());
        Long i= Long.valueOf(ComboBoxAlgFx11.getValue());
        List<RecommendedItem> recommendations = recommender.recommend(i,Nbr);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
            data.add(new dataInfo(ComboBoxAlgFx11.getValue(), recommendation.getItemID() + "", recommendation.getValue() + ""));

            double Mae = 0;
            double Rmse = 0;
            int y = Integer.valueOf(nbrtest110.getValue());
            Double x = Double.valueOf(ComboboxAlgoFx2.getValue());
            for (int j = 1; j <= y; j++) {
                RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
                RecommenderBuilder builder = new MyRecommenderBuilder();

                result11 = evaluator.evaluate(builder, null, model, sliderFx11.getValue(), x);
                System.out.println(result11);
                Mae=Mae+result11/2;
                RecommenderEvaluator rmsevaluator = new RMSRecommenderEvaluator();
                double score11 = evaluator.evaluate(builder, null, model, sliderFx11.getValue(), x);
                System.out.println(score11);
                Rmse=Rmse+score11/2;
                long t = System.currentTimeMillis();
                System.out.println("debut " + (start));
                System.out.println("fin " + (t));
                d = t - start;
                System.out.println(t - start);
            }
            System.out.println(Mae);
            System.out.println(Rmse);
            result11=Mae;
            score11=Rmse;
        }
    }




    static class MyRecommenderBuilder implements RecommenderBuilder {
        public static String typeFactorizer=ComboboxAlgoFx11.getValue();
        public Recommender buildRecommender(DataModel dataModel) throws TasteException {
        ALSWRFactorizer factorizer = new ALSWRFactorizer(dataModel, 50, 0.065, 15);
        ParallelSGDFactorizer factorizer2 = new ParallelSGDFactorizer(dataModel,10,0.1,1);
        SVDPlusPlusFactorizer factorizer3 = new SVDPlusPlusFactorizer(dataModel,10,10);
        SVDRecommender recommender;

        recommender = new SVDRecommender(dataModel, factorizer);

        if(typeFactorizer == "ParallelSGDFactorizer")
        recommender  = new SVDRecommender(dataModel, factorizer);

        else if(typeFactorizer == "SVDPlusPlusFactorizer")
        recommender = new SVDRecommender(dataModel, factorizer);
            return new SVDRecommender(dataModel, factorizer);

        }}
}








