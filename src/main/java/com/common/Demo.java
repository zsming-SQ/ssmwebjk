package com.common;

public class Demo{
	public static void main(String[] args){
		int[] arr = {2,7,2,1,5,75,3,9};
		sort1(arr);
		print(arr);
		
	}

	/**
		���������  ð������:��������Ԫ�����αȽϽ���
	*/
	public static void sort(int[] arr ){
	
		int temp = 0;
		for(int j=arr.length;j>0;j--){
			for(int i=1;i<j;i++){
				if(arr[i-1]>arr[i]){
					temp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = temp;
				}
			}
		}
		
	}

	/**
			ѡ�����򷨣�ѡ��ĳ��Ԫ�ظ�������Ԫ�����αȽϽ���
		*/
	public static void sort1(int[] arr){
		int temp = 0;
		for(int j=0;j<arr.length;j++){
			for(int i=j+1;i<arr.length;i++){
				if(arr[j]>arr[i]){
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		
	}

	/**
		��������
		���豻���Ԫ����߶�������ģ��ñ����Ԫ��������ߵ�Ԫ�ش�������ʼ���αȽϣ�
		�Ƚϵ�Ԫ������ȱ����λ��Ͱ����Ԫ�����ƣ�ֱ���Ƚϵ�Ԫ��С�ڱ��Ԫ�أ��ѱ��Ԫ�ط�������Ƚϵ�
		Ԫ���ұߵĿ�λ�ϡ�Ȼ�󱻱��Ԫ���±�����һλ��
	*/	
	public static void sort2(int[] arr){
	/*	for(int i = 1;i <arr.length;i++){
			for(int j=i-1;j>0;j--){
				if(){}
			}
		}*/
		
	}

	public static void print(int... arr){
		for(int x:arr){
			System.out.print(x+",");
		}
		System.out.println();
	}

	public static int binarySearch(int[] arr,int fromIndex,int toIndex,int key){
		/**
			���ַ����ң����������飬�����м��Ԫ�ؿ�ʼ����key
		*/
		int low=fromIndex;
		int high= toIndex;
		while(low<=high){
			int mid=(low+high)>>>1;
			int midval = arr[mid];
			if(key>midval){
				low=mid+1;
			}else if(key<midval){
				high=mid-1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
}