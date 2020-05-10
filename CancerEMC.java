import weka.classifiers.meta.Bagging;
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
	//	System.out.println(dataset1.toSummaryString());
		System.out.println("For Dataset SD1");
		
		//cancerEMC(dataset1);
		
		
		Instances dataset2= new Instances(new BufferedReader(new FileReader("Sub-dataset2 (SD2).arff")));
	//	System.out.println(dataset2.toSummaryString());
		System.out.println("For Dataset SD2");
		
		//cancerEMC(dataset2);
		
		
		
		Instances dataset3= new Instances(new BufferedReader(new FileReader("Sub-dataset3 (SD3).arff")));
	//	System.out.println(dataset3.toSummaryString());
		System.out.println("For Dataset SD3");
		
	//	cancerEMC(dataset3);
		
		
		Instances dataset4= new Instances(new BufferedReader(new FileReader("Sub-dataset4 (SD4).arff")));
	//	System.out.println(dataset4.toSummaryString());
		System.out.println("For Dataset SD4");
		
	//	cancerEMC(dataset4);
		
		/*
		Instances dataset5= new Instances(new BufferedReader(new FileReader("BinaryRFfeature.arff")));
		System.out.println(dataset5.toSummaryString());
		System.out.println("For BinaryRFfeature");
		
    	cancerEMC(dataset5); 
		
		Instances dataset6= new Instances(new BufferedReader(new FileReader("CamcerTypeDataset19Feature.arff")));
		System.out.println(dataset6.toSummaryString());
		System.out.println("For CamcerTypeDataset19Feature");
		
		cancerEMC(dataset6);*/
		
		
		Instances Smote= new Instances(new BufferedReader(new FileReader("CamcerTypeDataset19FeatureClassBalance.arff")));
		System.out.println(Smote.toSummaryString());
		System.out.println("For After SMOTE");
		
		cancerEMC(Smote);
		
	/*	
		System.out.println("After SMOTE for Dataset SD4\\n");
		String[] options=new String[2];
		options[0]="-P"; options[1]="500";
	    SMOTE smote=new SMOTE();
	    smote.setOptions(options);
	    smote.setInputFormat(dataset6);
		Instances newdata=Filter.useFilter(dataset6, smote);
		cancerEMC(newdata);
		*/
		
		System.out.println("The END");

		
	}
	
	public static void cancerEMC(Instances dataset) throws Exception {
		dataset.setClassIndex(dataset.numAttributes()-1);
		System.out.println(dataset.attribute(dataset.numAttributes()-1));
		int n=dataset.numInstances();
		String s=String.valueOf(n);
		int a=dataset.numAttributes()-1;
		String e=String.valueOf(a);
		
		String[] options=new String[2];
		options[0]="-B"; options[1]=s;
		options[0]="-R"; options[1]="1-"+e;
		Discretize discretize=new Discretize();
		discretize.setOptions(options);
		discretize.setInputFormat(dataset);
		Instances newdata=Filter.useFilter(dataset, discretize);
		
		Bagging b=new Bagging();
		b.setClassifier(new AODE());
		b.setNumIterations(10);
		b.buildClassifier(newdata);
		
		Evaluation evl=new Evaluation(newdata);
		Random rand=new Random(1);
		int folds=10;
		evl.crossValidateModel(b, newdata, folds, rand);
		//System.out.println("AUC="+evl.areaUnderROC(newdata.classIndex()));
		
		System.out.println(evl.toSummaryString());
		System.out.println(evl.toMatrixString());
		System.out.println(evl.getMetricsToDisplay());
		//System.out.println(evl.toClassDetailsString())
		
		
		String txt=null;
		try {
			fileWriter = new FileWriter("PREDICT.txt");
			//inherited method from java.io.OutputStreamWriter 
			for (int i = 0; i < evl.predictions().size(); i++) {
				
				//fileWriter.write(evl.predictions().get(i));
				//fileWriter.write(67);
				txt=evl.predictions().get(i).toString();
				//System.out.println(txt+"\n");
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

