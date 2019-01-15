package recommandation;
 
import org.apache.mahout.cf.taste.common.TasteException; 
import org.apache.mahout.cf.taste.eval.IRStatistics; 
import org.apache.mahout.cf.taste.eval.RecommenderBuilder; 
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator; 
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator; 
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel; 
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood; 
import org.apache.mahout.cf.taste.recommender.Recommender; 
import org.apache.mahout.cf.taste.similarity.UserSimilarity; 
import org.apache.mahout.common.RandomUtils; 
 
import java.io.File;

import static recommandation.FXMLDocumentController.ComboboxAlgoFx;
import static recommandation.FXMLDocumentController.comboboxneighberFx;
import static recommandation.FXMLDocumentController.pathh;

/**
 * @author hichem Bedjaoui
 */
public class  RapPre {

 
    public static void main(String[] args) throws Exception { 
        RandomUtils.useTestSeed(); 
        DataModel model = new FileDataModel(new File(pathh));
        RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();   
        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() { 
            @Override 

                    public Recommender buildRecommender(DataModel dataModel) throws TasteException {

               String typeSimilarity=ComboboxAlgoFx.getValue();
                String typeNeighborhood=comboboxneighberFx.getValue();
                        UserSimilarity   similarity = new PearsonCorrelationSimilarity(dataModel);
                        if(typeSimilarity=="EuclideanDistanceSimilarity")
                            similarity = new EuclideanDistanceSimilarity(dataModel);
                        if(typeSimilarity=="TanimotoCoefficientSimilarity")
                            similarity = new TanimotoCoefficientSimilarity(dataModel);
                        if(typeSimilarity=="LogLikelihoodSimilarity")
                            similarity = new LogLikelihoodSimilarity(dataModel);
                        if(typeSimilarity=="SpearmanCorrelationSimilarity")
                            similarity = new SpearmanCorrelationSimilarity(dataModel);
                        if(typeSimilarity=="CityBlockSimilarity")
                            similarity = new CityBlockSimilarity(dataModel);
                        UserNeighborhood neighborhood = new NearestNUserNeighborhood(1000, similarity, dataModel);
                        if(typeNeighborhood=="ThresholdUserNeighborhood")
                            neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
                        return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

                    }

        }; 
        IRStatistics stats = evaluator.evaluate(recommenderBuilder, null, model, null, 2, 
                GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
        System.out.println(stats.getPrecision());        
        System.out.println(stats.getRecall());
    } 
 
}
