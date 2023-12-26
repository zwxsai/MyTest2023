package com.example.mytest2023.Api.Utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by zwx on 2018/5/16.
 * Describer: 下载进度的拦截器（监听器）
 */
public class ProgressResponseBody extends ResponseBody {

	private ResponseBody mResponseBody;
	private BufferedSource mBufferedSource;
	private IProgressListener mListener;

	public ProgressResponseBody(ResponseBody mResponseBody, IProgressListener mListener) {
		this.mResponseBody = mResponseBody;
		this.mListener = mListener;
	}

	@Override
	public MediaType contentType() {
		return mResponseBody.contentType();
	}

	@Override
	public long contentLength() {
		return mResponseBody.contentLength();
	}

	@Override
	public BufferedSource source() {
		if (mBufferedSource == null)
			mBufferedSource = Okio.buffer(getSource(mResponseBody.source()));
		return mBufferedSource;
	}

	private Source getSource(Source source) {
		return new ForwardingSource(source) {
			long totalSize = 0;
			long sum = 0;

			@Override
			public long read(Buffer sink, long byteCount) throws IOException {
				if (totalSize == 0) totalSize = contentLength();

				long len = super.read(sink, byteCount);
				if (len == -1) {
					mListener.onDone(totalSize);
				} else {
					sum += len;
					int progress = (int) ((sum * 1.00 / totalSize) * 100);
					mListener.onProgress(progress);
				}

				return len;
			}
		};
	}

	public interface IProgressListener {
		public void onProgress(int progress);

		public void onDone(long totalSize);
	}
}
