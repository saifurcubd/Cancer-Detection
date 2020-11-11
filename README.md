# Cancer-Detection
**CancerEMC: frontline non-invasive cancer screening from circulating protein biomarkers and mutations in cell-free DNA**

Abstract:

Motivation: The early detection of cancer through accessible blood tests can foster early patient interventions. Although there are developments in cancer detection from cell-free DNA (cfDNA), its accuracy remains speculative. Given its central importance with broad impacts, we aspire to address the challenge.  
Methods: A bagging Ensemble Meta Classifier (CancerEMC) is proposed for early cancer detection based on circulating protein biomarkers and mutations in cfDNA from the blood. CancerEMC is generally designed for both binary cancer detection and multi-class cancer type localization. It can address the class imbalance problem in multi-analyte blood test data based on robust oversampling and adaptive synthesis techniques.
Results: Based on the clinical blood test data, we observe that the proposed CancerEMC has outperformed other algorithms and state-of-the-arts studies (including CancerSEEK published in Science, 2018) for cancer detection. The results reveal that one of our proposed methods (i.e., CancerEMC) can achieve the best performance result for both binary cancer classification with 99.1748% accuracy (AUC=0.999) and localized multiple cancer detection with the 73.16% accuracy (AUC=0.936). Addressing the class imbalance issue with oversampling techniques, the accuracy can be increased to 91.4966% (AUC=0.992), where the state-of-the-art method can only be estimated at 69.64% (AUC=0.921). Similar results can also be observed on independent and isolated testing data.

**Data directory and Files:**

**Datasets:**\
Dataset1: first dataset that collected from Cohen et al. for binary cancer detection.\
Dataset2: Second dataset that was collected from Cohen et al. for localized cancer detection. \
FullDataset: It consists of all patient's data for both binary and localized cancer detection.\
SD1: sub-dataset1 consists of 1817 patients for binary cancer detection, as same as dataset1.\
SD2: sub-dataset2 consist of 1817 patients for both binary and localize cancer detection that obtained from FullDataset.\
SD3: sub-dataset3 consist of only 1005 cancer patients for localizing cancer detection that obtained from FullDataset.\
SD4: sub-dataset4 consists of only 626 cancer patients for localized cancer detection, as same as dataset2.\

**Related Data:**\
All associated data files are here.

**Figures:**\
Contain all figures are available in high resolution.




