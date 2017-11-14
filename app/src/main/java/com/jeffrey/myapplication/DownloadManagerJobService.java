package com.jeffrey.myapplication;

import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by jefchen on 9/19/17.
 */

public class DownloadManagerJobService extends JobService {
    private static final String TAG = DownloadManagerJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "starting DownloadManagerJobService job");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.d(TAG, "stopping DownloadManagerJobService job");
        return false;
    }
}
