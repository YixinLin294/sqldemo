package com.shenlanbao.sqldemo.multitest;

public class ShareCalculator {

    public static double[] calculatePercentage(double[] parts, double total) {
        double[] results = new double[parts.length];
        double[] reminders = new double[parts.length];

        double totalPart = 0;
        for (double part : parts) {
            totalPart += part;
        }

        double hareQuota = totalPart / total;
        double allocatedPart = 0;

        for (int i = 0; i < parts.length; i++) {
            double voteDivHareQuota = parts[i] / hareQuota;
            results[i] = Math.floor(voteDivHareQuota);
            reminders[i] = voteDivHareQuota - results[i];
            allocatedPart += results[i];
        }

        double leftPart = total - allocatedPart;

        for (int i = 0; i < leftPart; i++) {
            double max = 0;
            int maxIndex = 0;
            for (int j = 0; j < reminders.length; j++) {
                if (reminders[j] > max) {
                    max = reminders[j];
                    maxIndex = j;
                }
            }
            results[maxIndex] += 1;
            reminders[maxIndex] = 0;
        }
        return results;
    }
}
