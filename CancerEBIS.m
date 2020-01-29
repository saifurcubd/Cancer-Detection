function [AUC]=CancerEBIS(vh,hi,mi,lo,vl)% initial values of vh=1.0, hi=0.75, mi=0.5, lo=0.25 and vl=
% a1=Omegascore
% a2=CA125
% a3=CA19-9
% a4=PRL            
% a5=HGF
% a6=OPN
  in=evalin('base','input');
 % in = table2array(in1);
  t=evalin('base','actualtest');
   %t= table2array(tc);
  %d1=evalin('base','rb');
  %d = table2array(d1);
  %fprintf('%d\n',5^6);
 % fprintf('%d\n',c);
  cancer=0.0;
  rf=0.0;
  p=0;
  o=1;
  error=0;
  e=1;
  for x=1:1817
      rb=0.0;
      c=1;
        for i=1:5
            for j=1:5
                for k=1:5
                    for l=1:5
                        for m=1:5
                            for n=1:5
                                f=[0.0,0.0,0.0,0.0,0.0];
                                f(i)=f(i)+1;             
                                f(j)=f(j)+1;             
                                f(k)=f(k)+1;             
                                f(l)=f(l)+1;             
                                f(m)=f(m)+1;             
                                f(n)=f(n)+1;
                                rb(c,1)=f(1)/6;
                                rb(c,2)=f(2)/6;
                                rb(c,3)=f(3)/6;
                                rb(c,4)=f(4)/6;
                                rb(c,5)=f(5)/6;
                                c=c+1;
                            end
                        end
                    end
                end
            end
        end
      rb=transpose(rb);
      a1=in(x,1); %Omega Score					
      a2=in(x,2); %CA-125 (U/ml)
      a3=in(x,3); %CA19-9 (U/ml)
      a4=in(x,4); %Prolactin (pg/ml)
      a5=in(x,5); %HGF (pg/ml)
      a6=in(x,6); %OPN (pg/ml)
      
      
      % for a1=Omega score
      rf(1)=0.0;rf(2)=0.0;rf(3)=0.0;rf(4)=0.0;rf(5)=0.0;
      if a1>=7.1
          rf(1)=1.00;
      elseif a1>=5.6 & a1<7.1
          rf(2)=(7.1-a1)/(7.1-5.6);
          rf(1)=(1.0-rf(2));
      elseif a1>=4.2 & a1<5.6
          rf(3)=(5.6-a1)/(5.6-4.2);
          rf(2)=(1.0-rf(3));
      elseif a1>=2.5 & a1<4.2
          rf(4)=(4.2-a1)/(4.2-2.5);
          rf(3)=(1.0-rf(4));
      elseif a1>=0.7 & a1<2.5
          rf(5)=(2.5-a1)/(2.5-0.7);
          rf(4)=(1.0-rf(5));
      elseif a1<0.7 
          rf(5)=1;
      end
      it(1,1)=rf(1); % VH
	  it(1,2)=rf(2);	          
      it(1,3)=rf(3);	          
      it(1,4)=rf(4); 	          
      it(1,5)=rf(5); %VL
      
      % for a2=CA-125 (U/ml)
      rf(1)=0.0;rf(2)=0.0;rf(3)=0.0;rf(4)=0.0;rf(5)=0.0;
      if a2>=164
          rf(1)=1.00;
      elseif a2>=41 & a2<164
          rf(2)=(164-a2)/(164-41);
          rf(1)=(1.0-rf(2));
      elseif a2>=25 & a2<41
          rf(3)=(41-a2)/(41-25);
          rf(2)=(1.0-rf(3));
      elseif a2>=15 & a2<25
          rf(4)=(25-a2)/(25-15);
          rf(3)=(1.0-rf(4));
      elseif a2>=5 & a2<15
          rf(5)=(15-a2)/(15-5);
          rf(4)=(1.0-rf(5));
      elseif a2<5 
          rf(5)=1;
      end
      it(2,1)=rf(1); % VH
	  it(2,2)=rf(2);	          
      it(2,3)=rf(3);	          
      it(2,4)=rf(4); 	          
      it(2,5)=rf(5); %VL
      
      % a3=CA19-9 (U/ml)
      rf(1)=0.0;rf(2)=0.0;rf(3)=0.0;rf(4)=0.0;rf(5)=0.0;
      if a3>=356
          rf(1)=1.00;
      elseif a3>=82 & a3<356
          rf(2)=(356-a3)/(356-82);
          rf(1)=(1.0-rf(2));
      elseif a3>=53 & a3<82
          rf(3)=(82-a3)/(82-53);
          rf(2)=(1.0-rf(3));
      elseif a3>=25 & a3<53
          rf(4)=(53-a3)/(53-25);
          rf(3)=(1.0-rf(4));
      elseif a3>=16 & a3<25
          rf(5)=(25-a3)/(25-16);
          rf(4)=(1.0-rf(5));
      elseif a3<16 
          rf(5)=1;
      end
      it(3,1)=rf(1); % VH
	  it(3,2)=rf(2);	          
      it(3,3)=rf(3);	          
      it(3,4)=rf(4); 	          
      it(3,5)=rf(5); %VL
      
      % for a4=PRL- Prolactin (pg/ml)
      rf(1)=0.0;rf(2)=0.0;rf(3)=0.0;rf(4)=0.0;rf(5)=0.0;
      if a4>=82164
          rf(1)=1.00;
      elseif a4>=48095 & a4<82164
          rf(2)=(82164-a4)/(82164-48095);
          rf(1)=(1.0-rf(2));
      elseif a4>=32314 & a4<48095
          rf(3)=(48095-a4)/(48095-32314);
          rf(2)=(1.0-rf(3));
      elseif a4>=22548 & a4<32314
          rf(4)=(32314-a4)/(32314-22548);
          rf(3)=(1.0-rf(4));
      elseif a4>=12781 & a4<22548
          rf(5)=(22548-a4)/(22548-12781);
          rf(4)=(1.0-rf(5));
      elseif a4<12781 
          rf(5)=1;
      end
      it(4,1)=rf(1); % VH
	  it(4,2)=rf(2);	          
      it(4,3)=rf(3);	          
      it(4,4)=rf(4); 	          
      it(4,5)=rf(5); %VL
      
      % for a5=HGF (pg/ml)
      rf(1)=0.0;rf(2)=0.0;rf(3)=0.0;rf(4)=0.0;rf(5)=0.0;
      if a5>=786
          rf(1)=1.00;
      elseif a5>=430 & a5<786
          rf(2)=(786-a5)/(786-430);
          rf(1)=(1.0-rf(2));
      elseif a5>=323 & a5<430
          rf(3)=(430-a5)/(430-323);
          rf(2)=(1.0-rf(3));
      elseif a5>=249 & a5<323
          rf(4)=(323-a5)/(323-249);
          rf(3)=(1.0-rf(4));
      elseif a5>=175 & a5<249
          rf(5)=(249-a5)/(249-175);
          rf(4)=(1.0-rf(5));
      elseif a5<175 
          rf(5)=1;
      end
      it(5,1)=rf(1); % VH
	  it(5,2)=rf(2);	          
      it(5,3)=rf(3);	          
      it(5,4)=rf(4); 	          
      it(5,5)=rf(5); %VL
      
      % for a6=OPN (pg/ml)
      rf(1)=0.0;rf(2)=0.0;rf(3)=0.0;rf(4)=0.0;rf(5)=0.0;
      if a6>=103329
          rf(1)=1.00;
      elseif a6>=76176 & a6<103329
          rf(2)=(103329-a6)/(103329-76176);
          rf(1)=(1.0-rf(2));
      elseif a6>=56295 & a6<76176
          rf(3)=(76176-a6)/(76176-56295);
          rf(2)=(1.0-rf(3));
      elseif a6>=36874 & a6<56295
          rf(4)=(56295-a6)/(56295-36874);
          rf(3)=(1.0-rf(4));
      elseif a6>=17453 & a6<36874
          rf(5)=(36874-a6)/(36874-17453);
          rf(4)=(1.0-rf(5));
      elseif a6<17453 
          rf(5)=1;
      end
      it(6,1)=rf(1); % VH
	  it(6,2)=rf(2);	          
      it(6,3)=rf(3);	          
      it(6,4)=rf(4); 	          
      it(6,5)=rf(5); %VL
      
      % Attribute weight initialization A1 to A6 and cancer
      aw=[1.0,1.0,1.0,1.0,1.0,1.0,1.0];
      
%       for i=1:6
% 	    brb(i,1)=it(i,1); 			  
%         brb(i,2)=it(i,2);
% 		brb(i,3)=it(i,3);
% 		brb(i,4)=it(i,4);
% 		brb(i,5)=it(i,5);
%       end
     
     md=0.0; 
    c=1;
    for i=1:5
    for j=1:5
        for k=1:5
            for l=1:5
                for m=1:5
                    for n=1:5
                        md(c)=it(1,i)*it(2,j)*it(3,k)*it(4,l)*it(5,m)*it(6,n); % matching Degree
                     %   X=sprintf('R%d: %f %f %f %f %f',c+1, rb(c,1),rb(c,2), rb(c,3), rb(c,4),rb(c,5));
                     %   disp(X)
                        c=c+1;
                    end
                end
            end
        end
    end
    end
    r=5^6;
    for i=1:r
        rw(i)=1.0;
    end
    
    sum=0.0;
    for i=1:r
	    sum=sum+md(i)*rw(i);
    end
    for i=1:r
        w(i)=(md(i)*rw(i))/sum;
    end
    for i=1:r	                
        m(1,i)=rb(1,i)*w(i);	                
        m(2,i)=rb(2,i)*w(i);
	    m(3,i)=rb(3,i)*w(i);
	    m(4,i)=rb(4,i)*w(i);
	    m(5,i)=rb(5,i)*w(i);
	    m(6,i)=1-(m(1,i)+m(2,i)+m(3,i)+m(4,i)+m(5,i));
    end
    
    for i=2:r
	    %k[i]=(1/(1-((m[1,i-1]*m[2,i])+(m[1]i-1]*m[3]i])+(m[1]i-1]*m[4[i])+(m[1[i-1]*m[5[i])+(m[2]i-1]*m[1[i])+(m[2]i-1]*m[3[i])+(m[2[i-1]*m[4[i])+(m[2]i-1]*m[5]i])+(m[3]i-1]*m[1[i])+(m[3,i-1]*m[2[i])+(m[3[i-1]*m[4[i])+(m[3[i-1]*m[5[i])+(m[4[i-1]*m[1[i])+(m[4[i-1]*m[2[i])+(m[4[i-1]*m[3[i])+(m[4]i-1]*m[5]i])+(m[5]i-1]*m[1[i])+(m[5[i-1]*m[2[i])+(m[5[i-1]*m[3][i])+ (m[5][i-1]*m[4][i]))));
	    k(i)=(1/(1-((m(1,i-1)*m(2,i))+(m(1,i-1)*m(3,i))+(m(1,i-1)*m(4,i))+(m(1,i-1)*m(5,i))+(m(2,i-1)*m(1,i))+(m(2,i-1)*m(3,i))+(m(2,i-1)*m(4,i))+(m(2,i-1)*m(5,i))+(m(3,i-1)*m(1,i))+(m(3,i-1)*m(2,i))+(m(3,i-1)*m(4,i))+(m(3,i-1)*m(5,i))+(m(4,i-1)*m(1,i))+(m(4,i-1)*m(2,i))+(m(4,i-1)*m(3,i))+(m(4,i-1)*m(5,i))+(m(5,i-1)*m(1,i))+(m(5,i-1)*m(2,i))+(m(5,i-1)*m(3,i))+(m(5,i-1)*m(4,i)))));
	    m(1,i)= k(i)*((m(1,i-1)*m(1,i))+(m(1,i-1)*m(6,i))+(m(6,i-1)*m(1,i)));
	    m(2,i)= k(i)*((m(2,i-1)*m(2,i))+(m(2,i-1)*m(6,i))+(m(6,i-1)*m(2,i)));
	    m(3,i)= k(i)*((m(3,i-1)*m(3,i))+(m(3,i-1)*m(6,i))+(m(6,i-1)*m(3,i)));
	    m(4,i)= k(i)*((m(4,i-1)*m(4,i))+(m(4,i-1)*m(6,i))+(m(6,i-1)*m(4,i)));
	    m(5,i)= k(i)*((m(5,i-1)*m(5,i))+(m(5,i-1)*m(6,i))+(m(6,i-1)*m(5,i)));
	    m(6,i)= k(i)*m(6,i-1)*m(6,i);
    end   
    brb(x,1)=m(1,r)/(1-m(6,r));
	brb(x,2)=m(2,r)/(1-m(6,r));
	brb(x,3)=m(3,r)/(1-m(6,r));
	brb(x,4)=m(4,r)/(1-m(6,r));
	brb(x,5)=m(5,r)/(1-m(6,r));
	cancer(x)=(brb(x,1)*vh)+(brb(x,2)*hi)+(brb(x,3)*mi)+(brb(x,4)*lo)+(brb(x,5)*vl);
    CancerClass(x)=0;
    if cancer(x)>=0.09
        CancerClass(x)=1;
    end
    
    if t(x)==CancerClass(x)
        p=p+1;
    else 
        error(e)=x;
        e=e+1;
    end
    Accuracy=p/o;
   % fprintf('PC = %d-%d-%d accuracy = %f\n',p,o-p,o,(p/o*100));
    o=o+1;
  end
  Accuracy=p/1817
  [X1,Y1,W,AUC] = perfcurve(t,cancer,'1');
  FPR=X1;
  TPR=Y1;
  plot(X1,Y1)
  xlabel('False positive rate')
  ylabel('True positive rate')
  title('ROC for Classification by CancerEBIS')
  label=logical(t);
end
