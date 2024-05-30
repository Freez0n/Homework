package org.example;

import java.util.Arrays;

public class HybridSort {

    public static void hybridSort(int[] arr) {
        hybridSort(arr, 0, arr.length - 1);
    }

    private static void hybridSort(int[] arr, int left, int right) {

        if (right - left < 16) {
            insertionSort(arr, left, right);
        }  else if (left < right) {
            int mid = (left + right) / 2;
            hybridSort(arr, left, mid);
            hybridSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void insertionSort(int[] arr, int left, int right) { //сортировка для коротких массивов
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) { //сортировка и слияние длинных массивов
        int[] temp = Arrays.copyOfRange(arr, left, right + 1);
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i - left] <= temp[j - left]) {
                arr[k++] = temp[i++ - left];
            } else {
                arr[k++] = temp[j++ - left];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++ - left];
        }

        while (j <= right) {
            arr[k++] = temp[j++ - left];
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6}; //тестовый массив для сортировки
        hybridSort(arr);

        System.out.println("Отсортированный массив:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}