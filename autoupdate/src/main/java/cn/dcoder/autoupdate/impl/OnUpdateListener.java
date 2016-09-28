package cn.dcoder.autoupdate.impl;

import cn.dcoder.autoupdate.beans.UpdateInfo;

/**
 * Created by Henry Chang on 2016/9/27.
 */
public interface OnUpdateListener {

    public void onPreCheck();

    public void onFinishCheck(UpdateInfo info);

    public void onStartDownload();

    public void onDownloading(int progress);

    public void onFinshDownload();
}
