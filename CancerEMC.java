import weka.classifiers.meta.Bagging;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;
import weka.filters.supervised.instance.SMOTE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import weka.classifiers.*;
import weka.classifiers.bayes.AODE;

public class CancerEMC{
	
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
		System.out.println(dataset4.toSummaryString());
		System.out.println("For Dataset SD4");
		
		cancerEMC(dataset4);
		
		
		System.out.println("After 500% SMOTE for Dataset SD4\\n");
		String[] options=new String[2];
		options[0]="-P"; options[1]="500";
	    SMOTE smote=new SMOTE();
	    smote.setOptions(options);
	    smote.setInputFormat(dataset4);
		Instances newdata=Filter.useFilter(dataset4, smote);
		
		cancerEMC(newdata);
		
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
		System.out.println(evl.toClassDetailsString(""));
		
		/*
		for (int i = 0; i < newdata.numInstances(); i++) {
			Instance ni=newdata.instance(i);
			b.classifyInstance(ni);
		}
		*/
	}

}

