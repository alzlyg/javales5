package ru.zlygostev;

public class MultithreadingExperiments {
    static final int size = 10000000;
    static final int h = size / 2;
    private float[] arr = new float[size];
    private float[] arr1 = new float[h];
    private float[] arr2 = new float[h];

    public void MultithreadingExperiments() {

    }

    final String startSingleThread() {
        String sReport = "Однопоточное время выполнения - ";
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        int j = (int) (System.currentTimeMillis() - a);
        sReport += Integer.toString(j) + " мс";
        return sReport;
    }

    private class ProcessArray1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < h; i++) {
                arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    private class ProcessArray2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < h; i++) {
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    final String startMultiThread() {
        String sReport = "Многопоточное время выполнения - ";
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        new Thread(new ProcessArray1()).start();
        new Thread(new ProcessArray2()).start();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        int j = (int) (System.currentTimeMillis() - a);
        sReport += Integer.toString(j) + " мс";
        return sReport;
    }

    final String start() {
        String sReport = "";
        sReport = startSingleThread();
        sReport += "\n" + startMultiThread();
        return sReport;
    }
}
