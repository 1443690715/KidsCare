package com.lanbiao.youxiaoyunfamily.loadimg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ImageShowView extends View implements Runnable {

	Bitmap mBitmap = null;

	int mBitmapWidth = 0;// ����ͼƬ�Ŀ�

	int mBitmapHeight = 0;// ����ͼƬ�ĸ�

	// �Ŵ���
	private double Scale = 5.0;

	Matrix mMatrix = new Matrix();

	public ImageShowView(Context context, Drawable map) {

		super(context);

		mBitmap = ((BitmapDrawable) map).getBitmap();

		mBitmapWidth = mBitmap.getWidth();

		mBitmapHeight = mBitmap.getHeight();

		// �����߳�

		new Thread(this).start();

	}

	public void setScale(double scale) {

		this.Scale = scale;

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		mMatrix.reset();

		mMatrix.postScale((float) Scale, (float) Scale);// ��������

		Bitmap mBitmap2 = Bitmap.createBitmap(mBitmap, 0, 0, mBitmapWidth,
				mBitmapHeight, mMatrix, true);

		// ������ת֮���ͼ��

		ImageShowView.drawImage(canvas, mBitmap2, (320 - mBitmapWidth) / 2, 10);

		mBitmap2 = null;

	}

	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {

			try {

				Thread.sleep(100);

			} catch (InterruptedException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			postInvalidate();

		}

	}

	private static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {

		// TODO Auto-generated method stub

		canvas.drawBitmap(bitmap, x, y, null);

	}

}