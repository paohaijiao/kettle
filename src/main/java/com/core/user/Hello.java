package com.core.user;

import org.apache.commons.lang.StringUtils;
import org.pentaho.di.core.ProgressNullMonitorListener;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.core.logging.LoggingBuffer;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.RepositoryDirectoryInterface;

import java.util.Date;

public class Hello {
//    public static void main(String[] args){
//        RepositoryDirectoryInterface directory = KettleDatabaseRepository.loadRepositoryDirectoryTree()
//                .findDirectory(jobPath);
//        JobMeta jobMeta = kettleDatabaseRepository.loadJob(jobName, directory, new ProgressNullMonitorListener(),
//                null);
//        job = new org.pentaho.di.job.Job(kettleDatabaseRepository, jobMeta);
//        job.setDaemon(true);
//        job.setLogLevel(LogLevel.DEBUG);
//        if (StringUtils.isNotEmpty(logLevel)) {
//            job.setLogLevel(Constant.logger(logLevel));
//        }
//        String exception = null;
//        Integer recordStatus = 1;
////            Date jobStartDate = null;
//        Date jobStopDate = null;
//        String logText = null;
//        try {
////                jobStartDate = new Date();
//            job.run();
//            job.waitUntilFinished();
//            jobStopDate = new Date();
//        } catch (Exception e) {
//            exception = e.getMessage();
//            recordStatus = 2;
//        } finally {
//            if (job.isFinished()) {
//                if (job.getErrors() > 0) {
//                    recordStatus = 2;
//                    if(null == job.getResult().getLogText() || "".equals(job.getResult().getLogText())){
//                        logText = exception;
//                    }
//                }
//                // 写入作业执行结果
//                StringBuilder allLogFilePath = new StringBuilder();
//                allLogFilePath.append(logFilePath).append("/").append(userId).append("/")
//                        .append(StringUtils.remove(jobPath, "/")).append("@").append(jobName).append("-log")
//                        .append("/").append(new Date().getTime()).append(".").append("txt");
//                String logChannelId = job.getLogChannelId();
//                LoggingBuffer appender = KettleLogStore.getAppender();
//                logText = appender.getBuffer(logChannelId, true).toString();
//                try {
//                    KJobRecord kJobRecord = new KJobRecord();
//                    kJobRecord.setRecordJob(Integer.parseInt(jobId));
//                    kJobRecord.setAddUser(Integer.parseInt(userId));
//                    kJobRecord.setLogFilePath(allLogFilePath.toString());
//                    kJobRecord.setRecordStatus(recordStatus);
//                    kJobRecord.setStartTime(executeTime);
//                    kJobRecord.setStopTime(jobStopDate);
//                    writeToDBAndFile(DbConnectionObject, kJobRecord, logText, executeTime, nexExecuteTime);
//                } catch ( Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    }
}
