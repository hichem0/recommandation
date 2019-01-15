package recommandation;

//import filtrage.test.MyRecommenderBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static java.util.stream.DoubleStream.builder;
import static recommandation.FXMLDocumentController.*;
import static sun.jvm.hotspot.asm.sparc.SPARCRegisters.O5;

import com.sun.jdi.IntegerValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import static javafxapplication7.FXMLDocumentController.RecommendationLoopp;
//import static javafxapplication7.FXMLDocumentController.pathh;
//import static org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.CachingUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
/*
 *
 * @author hichem Bedjaoui
 *
 */

public class UserBased {

    long d;
    long start = System.currentTimeMillis();
    public double result;
    public double score;
     double Mae;
     double Rmse;
    public static ObservableList<dataInfo> data = FXCollections.observableArrayList();

    public ObservableList<dataInfo> getItem() {
        return data;
    }

        public double getEval(){return result;}
        public double getrmsEval(){
            return score;
        }
        public double getTime(){return d;}

        public String typeSimilarity=ComboboxAlgoFx.getValue();
        public String typeNeighborhood=comboboxneighberFx.getValue();
    Double Nbr1=Double.valueOf(nbrRecomFx111.getText());
    public void test() throws IOException, TasteException {

        DataModel model = new FileDataModel(new File(pathh));

        UserSimilarity   similarity = new PearsonCorrelationSimilarity(model);
        if(typeSimilarity=="EuclideanDistanceSimilarity")
            similarity = new EuclideanDistanceSimilarity(model);
        if(typeSimilarity=="TanimotoCoefficientSimilarity")
             similarity = new TanimotoCoefficientSimilarity(model);
        if(typeSimilarity=="LogLikelihoodSimilarity")
             similarity = new LogLikelihoodSimilarity(model);
        if(typeSimilarity=="SpearmanCorrelationSimilarity")
             similarity = new SpearmanCorrelationSimilarity(model);
        if(typeSimilarity=="CityBlockSimilarity")
             similarity = new CityBlockSimilarity(model);

        UserNeighborhood neighborhood = new NearestNUserNeighborhood(1, similarity, model);
        if(typeNeighborhood=="ThresholdUserNeighborhood")
             neighborhood = new ThresholdUserNeighborhood( Nbr1, similarity, model);

        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        
        //int a = Integer.parseInt(RecommendationLoopp.getText());
      //  for (int i = 1; i < ; i++) {

        if(ComboBoxAlgFx.getValue()=="Tout"){


            for (int i = 1; i <944; i++) {

                int Nbr=Integer.valueOf(nbrRecomFx.getText());
                List<RecommendedItem> recommendations = recommender.recommend(i,Nbr);
                for (RecommendedItem recommendation : recommendations) {

                    System.out.print("recommendation:");
                    System.out.println(recommendation);
                    data.add(new dataInfo(i+"", recommendation.getItemID()+"", recommendation.getValue()+""));
                }
            }

        }


        Long i= Long.valueOf(ComboBoxAlgFx.getValue());
         int Nbr=Integer.valueOf(nbrRecomFx.getText());

            List<RecommendedItem> recommendations = recommender.recommend(i,Nbr);
            for (RecommendedItem recommendation : recommendations) {
                
                System.out.print("recommendation:");
                System.out.println(recommendation);
                data.add(new dataInfo(ComboBoxAlgFx.getValue(), recommendation.getItemID()+"", recommendation.getValue()+""));
            }
       // }
        double  Mae=0;
        double Rmse=0;
        int y = Integer.valueOf(nbrtest0.getValue());
        Double x = Double.valueOf(ComboboxAlgoFx111.getValue());
        for (int j = 1; j<=y; j++) {

            RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

            RecommenderBuilder builder = new UserBased.MyRecommenderBuilder();
            result = evaluator.evaluate(builder, null, model, sliderFx.getValue(), x);
            System.out.println(result);
            Mae = Mae + result /y;
            RecommenderEvaluator rmsevaluator = new RMSRecommenderEvaluator();
            score = rmsevaluator.evaluate(builder, null, model, sliderFx.getValue(), x);
            System.out.println(score);
            Rmse = Rmse + score / y;


            long t = System.currentTimeMillis();
            System.out.println("debut " + (start));
            System.out.println("fin " + (t));
            d = t - start;

        }
        System.out.println(Mae);
        System.out.println(Rmse);
        result=Mae;
        score=Rmse;

/*        RecommenderIRStatsEvaluator evaluator1 = new GenericRecommenderIRStatsEvaluator();
        IRStatistics stats = evaluator1.evaluate(builder,
                null,
                model,
                null,
                1,
                GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD,
                1);
        System.out.println( "Precision"+stats.getPrecision() );


        System.out.println("Recall: " + stats.getRecall());
*/
    }

    static class MyRecommenderBuilder implements RecommenderBuilder {
        public String typeSimilarity = ComboboxAlgoFx.getValue();
        public String typeNeighborhood = comboboxneighberFx.getValue();

        public Recommender buildRecommender(DataModel dataModel) throws TasteException {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            if (typeSimilarity == "EuclideanDistanceSimilarity")
                similarity = new EuclideanDistanceSimilarity(dataModel);
            if (typeSimilarity == "TanimotoCoefficientSimilarity")
                similarity = new TanimotoCoefficientSimilarity(dataModel);
            if (typeSimilarity == "LogLikelihoodSimilarity")
                similarity = new LogLikelihoodSimilarity(dataModel);
            if (typeSimilarity == "SpearmanCorrelationSimilarity")
                similarity = new SpearmanCorrelationSimilarity(dataModel);
            if (typeSimilarity == "CityBlockSimilarity")
                similarity = new CityBlockSimilarity(dataModel);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(400, similarity, dataModel);
            if (typeNeighborhood == "ThresholdUserNeighborhood")
                neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
            return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

        }


    }

    }



