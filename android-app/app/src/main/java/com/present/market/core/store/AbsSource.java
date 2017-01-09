package com.present.market.core.store;

import android.os.AsyncTask;
import android.support.annotation.WorkerThread;

import com.present.market.core.base.AbsObj;
import com.present.market.core.base.AppCallback;
import com.present.market.core.base.AppEx;
import com.present.market.core.base.AppType;

public abstract class AbsSource<Type> extends AbsObj {
    protected final Class<Type> classObj;
    public AbsSource(Class<Type> classObj) {
        super();
        log().debug("classObj=%s", classObj);
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

    public final class LoadTask extends AsyncTask<Void, Void, TaskResult<Type>> {
        private final AppCallback<Type> mAppCallback;
        public LoadTask(AppCallback<Type> appCallback) {
            this.mAppCallback = appCallback;
        }

        public TaskResult<Type> doInBackground(Void... params) {
            TaskResult<Type> taskResult = new TaskResult();
            AbsSource.this.onLoadData(taskResult);
            return taskResult;
        }

        public void onPostExecute(TaskResult<Type> taskResult) {
            if (taskResult.isError()) {
                this.mAppCallback.onError(taskResult.getError());
                return;
            }
            this.mAppCallback.onResult(taskResult.getResult());
        }
    }
    //=============================================================================================

    public final class SaveTask extends AsyncTask<Void, Void, TaskResult<Void>> {
        private final Type mData;
        private final AppCallback<Type> mAppCallback;
        public SaveTask(Type data, AppCallback<Type> appCallback) {
            this.mData = data;
            this.mAppCallback = appCallback;
        }

        public TaskResult<Void> doInBackground(Void... params) {
            TaskResult<Void> taskResult = new TaskResult();
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

    public class TaskResult<Result> extends AbsObj {
        private AppEx mAppEx;
        private Result mResult;
        private boolean error;

        public AppEx getError() {
            log().debug("getError");
            return this.mAppEx;
        }

        public Result getResult() {
            log().debug("getResult");
            return this.mResult;
        }

        public void setError(AppEx appEx) {
            log().debug("setError");
            this.mAppEx = appEx;
        }

        public void setResult(Result result) {
            log().debug("setResult");
            this.mResult = result;
        }

        public boolean isError() {
            return AppType.OBJ_IS_NOT_NULL(this.mAppEx);
        }
    }
}
