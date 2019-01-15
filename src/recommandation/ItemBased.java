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
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static recommandation.FXMLDocumentController.*;



/**
 * Created by hichem Bedjaoui on 2017-05-14.
 */
public class ItemBased {
    long  f;
    long start=System.currentTimeMillis();
    public static double result1;
    public static double score1;
    public static double Rmse;
    public static double Mae;
    public  static ObservableList<dataInfo> data = FXCollections.observableArrayList();
    public ObservableList<dataInfo> getItem(){
        return data;
    }

    public double getEval(){return result1;}
    public double getrmsEval(){return score1;}
    public double getTime(){return f;}
    public String typeSimilarity=ComboboxAlgoFx1.getValue();
    public void test() throws IOException, TasteException {

        DataModel model = new FileDataModel(new File(pathh));

        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
        if(typeSimilarity=="EuclideanDistanceSimilarity")
            similarity = new EuclideanDistanceSimilarity(model, Weighting.UNWEIGHTED);
        if(typeSimilarity=="TanimotoCoefficientSimilarity")
            similarity = new TanimotoCoefficientSimilarity(model);
        if(typeSimilarity=="LogLikelihoodSimilarity")
            similarity = new LogLikelihoodSimilarity(model);

        if(typeSimilarity=="CityBlockSimilarity")
            similarity = new CityBlockSimilarity(model);



        ItemBasedRecommender recommender = new GenericItemBasedRecommender(model,  similarity);


        //int a = Integer.parseInt(RecommendationLoopp.getText());
        /*/  for (int i = 1; i < ; i++) {




        Long i= Long.valueOf(ComboBoxAlgFx1.getValue());
        int Nbr=Integer.valueOf(nbrRecomFx1.getText());
        List<RecommendedItem> recommendations = recommender.recommend(i,Nbr);
        for (RecommendedItem recommendation : recommendations) {

            System.out.print("recommendation:");
            System.out.println(recommendation);
            data.add(new dataInfo(ComboBoxAlgFx1.getValue(), recommendation.getItemID()+"", recommendation.getValue()+""));
        }
        /
            }
        */

        if(ComboBoxAlgFx1.getValue()=="Tout"){


          for (int i = 1; i <944; i++) {

            int Nbr=Integer.valueOf(nbrRecomFx1.getText());
            List<RecommendedItem> recommendations = recommender.recommend(i,Nbr);
            for (RecommendedItem recommendation : recommendations) {

                System.out.print("recommendation:");
                System.out.println(recommendation);
                data.add(new dataInfo(i+"", recommendation.getItemID()+"", recommendation.getValue()+""));
            }
          }

        }
        else{

            Long i= Long.valueOf(ComboBoxAlgFx1.getValue());
            int Nbr=Integer.valueOf(nbrRecomFx1.getText());
            List<RecommendedItem> recommendations = recommender.recommend(i,Nbr);
            for (RecommendedItem recommendation : recommendations) {

                System.out.print("recommendation:");
                System.out.println(recommendation);
                data.add(new dataInfo(ComboBoxAlgFx1.getValue(), recommendation.getItemID()+"", recommendation.getValue()+""));
            }

        }


        double  Mae=0;
        double Rmse=0;
        int y = Integer.valueOf(nbrtest10.getValue());
        Double x = Double.valueOf(ComboboxAlgoFx1111.getValue());
        for (int j = 1; j<=y; j++) {


            RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

            RecommenderBuilder builder = new ItemBased.MyRecommenderBuilder();
            result1 = evaluator.evaluate(builder, null, model, sliderFx1.getValue(), x);
            System.out.println(result1);
            Mae = Mae + result1 / y;
            RecommenderEvaluator rmsevaluator = new RMSRecommenderEvaluator();
            score1 = rmsevaluator.evaluate(builder, null, model, sliderFx1.getValue(), x);
            System.out.println(score1);
            Rmse = Rmse + score1 / y;
            long t = System.currentTimeMillis();
            System.out.println("debut " + (start));
            System.out.println("fin " + (t));
            f = t - start;
            System.out.println(t - start);

        }
            System.out.println(Mae);
            System.out.println(Rmse);

        result1=Mae;
        score1=Rmse;

    }

    static class MyRecommenderBuilder implements RecommenderBuilder {
        public String typeSimilarity=ComboboxAlgoFx1.getValue();

        public Recommender buildRecommender(DataModel dataModel) throws TasteException {
            ItemSimilarity   similarity = new PearsonCorrelationSimilarity(dataModel);
            if(typeSimilarity=="EuclideanDistanceSimilarity")
                similarity = new EuclideanDistanceSimilarity(dataModel);
            if(typeSimilarity=="TanimotoCoefficientSimilarity")
                similarity = new TanimotoCoefficientSimilarity(dataModel);
            if(typeSimilarity=="LogLikelihoodSimilarity")
                similarity = new LogLikelihoodSimilarity(dataModel);

            if(typeSimilarity=="CityBlockSimilarity")
                similarity = new CityBlockSimilarity(dataModel);

            return new GenericItemBasedRecommender(dataModel,  similarity);

        }
    }

}

