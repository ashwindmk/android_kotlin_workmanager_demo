package com.ashwin.android.example.workmanagerdemo

import android.app.Activity
import android.os.Bundle
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        schedule_button.setOnClickListener {
            enqueueWork()
        }

        schedule_repeat_button.setOnClickListener {
            enqueueRepeatingWork()
        }
    }

    private fun enqueueWork() {
        val workManager: WorkManager = WorkManager.getInstance()

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workRequest: OneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }

    private fun enqueueRepeatingWork() {
        val workManager: WorkManager = WorkManager.getInstance()

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workRequest: PeriodicWorkRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }
}
