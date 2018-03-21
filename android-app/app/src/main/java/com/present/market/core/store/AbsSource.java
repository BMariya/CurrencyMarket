package com.present.market.core.store;

import android.os.AsyncTask;
import android.support.annotation.WorkerThread;

import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppEx;

public abstract class AbsSource<Type> {
    final Class<Type> classObj;
    AbsSource(Class<Type> classObj) {
        super();
        this.classObj = classObj;
    }

    public final void load(AppCallback<Type> appCallback) {
        new LoadTask(appCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public final void save(Type data, AppCallback<Type> appCallback) {
        new SaveTask(data, appCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @WorkerThread
    protected abstract void onLoadData(TaskResult<Type> taskResult);

    @WorkerThread
    protected abstract void onSaveData(Type data, TaskResult<Void> taskResult);
    //=============================================================================================

    private final class LoadTask extends AsyncTask<Void, Void, TaskResult<Type>> {
        private final AppCallback<Type> mAppCallback;
        private LoadTask(AppCallback<Type> appCallback) {
            super();
            this.mAppCallback = appCallback;
        }

        public TaskResult<Type> doInBackground(Void... params) {
            TaskResult<Type> taskResult = new TaskResult<>();
            AbsSource.this.onLoadData(taskResult);
            return taskResult;
        }

        public void onPostExecute(TaskResult<Type> taskResult) {
            if (taskResult.isError()) {
                this.mAppCallback.onError(taskResult.getError());
                return;
            }
            if (taskResult.getResult() == null) return;
            this.mAppCallback.onResult(taskResult.getResult());
        }
    }
    //=============================================================================================

    private final class SaveTask extends AsyncTask<Void, Void, TaskResult<Void>> {
        private final Type mData;
        private final AppCallback<Type> mAppCallback;
        private SaveTask(Type data, AppCallback<Type> appCallback) {
            super();
            this.mData = data;
            this.mAppCallback = appCallback;
        }

        public TaskResult<Void> doInBackground(Void... params) {
            TaskResult<Void> taskResult = new TaskResult<>();
            AbsSource.this.onSaveData(this.mData, taskResult);
            return taskResult;
        }

        public void onPostExecute(TaskResult<Void> taskResult) {
            if (taskResult.isError()) {
                this.mAppCallback.onError(taskResult.getError());
                return;
            }
            this.mAppCallback.onResult(null);
        }
    }
    //=============================================================================================

    final class TaskResult<Result> {
        private AppEx mAppEx;
        void setError(AppEx appEx) {
            this.mAppEx = appEx;
        }

        private AppEx getError() {
            return this.mAppEx;
        }

        private boolean isError() {
            return this.mAppEx != null;
        }

        private Result mResult;
        private Result getResult() {
            return this.mResult;
        }

        void setResult(Result result) {
            this.mResult = result;
        }
    }
}
