import weka.classifiers.meta.Bagging;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;
import weka.filters.supervised.instance.SMOTE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import weka.classifiers.*;
import weka.classifiers.bayes.AODE;
import weka.classifiers.evaluation.Prediction;


public class CancerEMC{
	   
	static FileWriter fileWriter = null;
	
	public static void main(String[] args) throws Exception {
		
		    
		// TODO Auto-generated method stub
		System.out.println("Welcome to CancerEMC\n");
		
		Instances dataset1= new Instances(new BufferedReader(new FileReader("Sub-dataset1 (SD1).arff")));
		System.out.println(dataset1.toSummaryString());
		System.out.println("For Dataset SD1");
		
		cancerEMC(dataset1);
		
		
		Instances dataset2= new Instances(new BufferedReader(new FileReader("Sub-dataset2 (SD2).arff")));
		System.out.println(dataset2.toSummaryString());
		System.out.println("For Dataset SD2");
		
		cancerEMC(dataset2);
		
		
		
		Instances dataset3= new Instances(new BufferedReader(new FileReader("Sub-dataset3 (SD3).arff")));
		System.out.println(dataset3.toSummaryString());
		System.out.println("For Dataset SD3");
		
		cancerEMC(dataset3);
		
		
		Instances dataset4= new Instances(new BufferedReader(new FileReader("Sub-dataset4 (SD4).arff")));
	//	System.out.println(dataset4.toSummaryString());
		System.out.println("For Dataset SD4 befor oversampling");
		
		cancerEMC(dataset4);
		
		/*
		Instances dataset5= new Instances(new BufferedReader(new FileReader("BinaryRFfeature.arff")));
		System.out.println(dataset5.toSummaryString());
		System.out.println("For BinaryRFfeature");
		
    	        cancerEMC(dataset5); 
		
		Instances dataset6= new Instances(new BufferedReader(new FileReader("CamcerTypeDataset19Feature.arff")));
		System.out.println(dataset6.toSummaryString());
		System.out.println("For CamcerTypeDataset19Feature");
		
		cancerEMC(dataset6);*/
		
		
		Instances Smote= new Instances(new BufferedReader(new FileReader("SMOTE_19_features_cancer_type_data.arff")));
	//	System.out.println(Smote.toSummaryString());
		System.out.println("For After SMOTE");
		
		cancerEMC(Smote);
		
	
		System.out.println("After SMOTE on Dataset SD4\\n");
		String[] options=new String[2];
		options[0]="-P"; options[1]="500"; //We can the set the require percentage to mirority classes
	        SMOTE smote=new SMOTE();
	        smote.setOptions(options);
	        smote.setInputFormat(dataset6);
		Instances newdata=Filter.useFilter(dataset6, smote);
		//cancerEMC(newdata);
		
		
		System.out.println("The END");
		
	}
	
	public  static Instances NumericToNominal(Instances dataProcessed, String variables) throws Exception {
		weka.filters.unsupervised.attribute.NumericToNominal convert = new weka.filters.unsupervised.attribute.NumericToNominal();
		String[] options = new String[2];
		options[0] = "-R";
		options[1] = variables;
		convert.setOptions(options);
		convert.setInputFormat(dataProcessed);
		Instances filterData = Filter.useFilter(dataProcessed, convert);
		return filterData;
	}
	
	public static void cancerEMC(Instances dataset) throws Exception {
		dataset.setClassIndex(dataset.numAttributes()-1);
		System.out.println(dataset.attribute(dataset.numAttributes()-1));
		int n=dataset.numInstances();
		String s=String.valueOf(n);
		int a=dataset.numAttributes()-1;
		String e=String.valueOf(a);
		Random rand=new Random();
		int folds=10;
		

		String[] options=new String[2];
		options[0]="-B"; options[1]=s;
		options[0]="-R"; options[1]="1-"+e;
		Discretize discretize=new Discretize();
		discretize.setOptions(options);
		discretize.setInputFormat(dataset);
		Instances newdata=Filter.useFilter(dataset, discretize);
		Instances data=NumericToNominal(dataset, "1-"+e);
		
		Bagging b=new Bagging();
		b.setClassifier(new AODE());
		b.setNumIterations(200);
		b.setBagSizePercent(100);
		b.setBatchSize("100");
		b.buildClassifier(newdata);
		
		AODE ad=new AODE();
		Evaluation evl=new Evaluation(newdata);
		
		evl.crossValidateModel(b, newdata, folds, rand);
		//System.out.println("AUC="+evl.areaUnderROC(newdata.classIndex()));
		
		System.out.println(evl.toSummaryString());
		System.out.println(evl.toMatrixString());
		System.out.println(evl.getMetricsToDisplay());
		System.out.println(evl.toClassDetailsString());
		
		/*
		//RandomForest
		System.out.println("Random Forest");
		RandomForest rf= new RandomForest();
		rf.setNumIterations(100);
		rf.setBagSizePercent(100);
		rf.setBatchSize("100");
		//rf.buildClassifier(newdata);
		
		Evaluation rfevl=new Evaluation(newdata);
		rfevl.crossValidateModel(rf, newdata, folds, rand);
		//System.out.println("AUC="+evl.areaUnderROC(newdata.classIndex()));
		
		System.out.println(rfevl.toSummaryString());
		System.out.println(rfevl.toMatrixString());
		System.out.println(rfevl.getMetricsToDisplay());
		System.out.println(rfevl.toClassDetailsString());
		
		
		//Ensemble
		Float[][] en=new Float[2000][12];
		for (int i = 0; i < rfevl.predictions().size(); i++) {
			//System.out.println(rfevl.predictions().get(i));
			//System.out.println(evl.predictions().get(i));
			String rp=rfevl.predictions().get(i).toString();
			String[] rfp=rp.split("\\s");
			String bp=evl.predictions().get(i).toString();
			String[] bap=bp.split("\\s");
			//String[] en = new String[12];			
			//Strcpy(en[0],rfp[1]);
			en[i][0]=Float.parseFloat(rfp[1]);
			en[i][1]=Float.parseFloat(rfp[2]);
			for (int j = 4,k=2; j < rfp.length; j++,k++) {
				en[i][k] = Float.parseFloat(rfp[j])+Float.parseFloat(bap[j]);
			}
			
			if(i==5) {
				for (int t = 0; t < rfp.length; t++) {
					System.out.print(bap[t]+",");
				}
				System.out.println();
				for (int t = 0; t < rfp.length; t++) {
					System.out.print(rfp[t]+",");
				}
				System.out.println();
				for (int t = 0; t <9; t++) {
					System.out.print(en[i][t]+",");
				}
				System.out.println();
				
			}
			//System.out.println(words[10]);
		    //NOM: 6.0 6.0 1.0 6.598375308324326E-6 3.6547166745450646E-5 2.2385806391974E-5 2.9441977028248506E-5 4.5127690617743464E-5 5.138797354068844E-7 0.9998593851041728
		}
		*/

		
		
		String txt=null;
		try {
			fileWriter = new FileWriter("PREDICT.txt");
			//inherited method from java.io.OutputStreamWriter 
			for (int i = 0; i < evl.predictions().size(); i++) {
				
				//fileWriter.write(evl.predictions().get(i));
				//fileWriter.write(67);
				txt=evl.predictions().get(i).toString();
			//	System.out.println(txt+"\n");
				fileWriter.write(txt+"\n");
		 
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();					
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
       
	
		/*Prediction sa = p.get(0);
		System.out.println(sa);
		System.out.println(sa.actual());
		System.out.println(sa.predicted());
		System.out.println(sa.weight());
		System.out.println(sa.toString());
		
		
		for (int i = 0; i < newdata.numInstances(); i++) {
			Instance ni=newdata.instance(i);
			b.classifyInstance(ni);
		}
		*/
	}
	

}

