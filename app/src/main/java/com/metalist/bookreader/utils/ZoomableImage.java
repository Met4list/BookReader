package com.metalist.bookreader.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class ZoomableImage extends androidx.appcompat.widget.AppCompatImageView {
    // Отслеживание касаний пальцев
    private ScaleGestureDetector scaleDetector;
    // текущий коэффициент масштабирования изображения
    private float scaleFactor = 1.f;

    // конструкторы
    public ZoomableImage(Context context) {
        super(context);
        init();
    }

    public ZoomableImage(Context context, AttributeSet set) {
        super(context, set);
        init();
    }

    public ZoomableImage(Context context, AttributeSet set, int defStyleAttr) {
        super(context, set, defStyleAttr);
        init();
    }

    // начальный коэффициент масштабирования
    private final float normalScaleFactor = 1f;
    // число на которое будет увеличиваться коэффициент масштабирования при анимации
    private final float increasingValue = .02f;
    // специальное число, которое уменьшает скорость масштабирования при уменьшении изображения
    private final double decreasingDivisor = 1.032;

    // минимальный коэффициент масштабирования
    private final float minScaleFactor = 0.05f;
    // максимальный коэффициент масштабирования
    private final float maxScaleFactor = 5.0f;

    // задержка для анимации
    private final long delay = 4L;

    // используем для выполнения анимации
    final Handler handler = new Handler(Looper.getMainLooper());

    // анимация возвращения к нормальному коэффициенту
    // срабатывает, когда мы уменьшаем изображение и отпускаем его
    final Runnable animationRunnable = new Runnable() {
        @Override
        public void run() {
            // пока текущий коэффициент масштабирования меньше нормального
            // увеличиваем его до нормального
            if (scaleFactor <= normalScaleFactor) {
                scaleFactor += increasingValue;
                // постоянно перерисовываем наше View
                invalidate();
                // снова выполняем анимацию, пока условие не станет ложным
                handler.postDelayed(this, delay);
            } else {
                // после завершения анимации, присваем текущему коэффициенту
                // начальное значение и перерисовываем View
                scaleFactor = normalScaleFactor;
                invalidate();
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // отслеживаем касания пальцев
        scaleDetector.onTouchEvent(ev);
        // запускаем анимацию, если мы уменьшили изображение и отпустили его
        if (ev.getAction() == MotionEvent.ACTION_UP && scaleFactor < normalScaleFactor) {
            // отменяем предыдущую анимацию
            handler.removeCallbacks(animationRunnable);
            // запускаем новую
            handler.postDelayed(animationRunnable, 4L);
        }
        return true;
    }

    private void init() {
        // инициализация нашего ScaleGestureDetector'а
        scaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.save();

        // масштабируем canvas вместе с нашим изображением
        // Обратите внимание, что мы передаем getWidth() / 2 и getHeight() / 2
        // для того, чтобы изображение масштабировалось от центра
        canvas.scale(scaleFactor, scaleFactor, getWidth() / 2, getHeight() / 2);

        super.onDraw(canvas);

        canvas.restore();
    }

    // чувствительность срабатывания, которую я подобрал экспериментально
    private final int sensitivity = 10;

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // если значения перемещения наших пальцев больше sensitivity то мы считаем,
            // что изображение масштабируется, в противном случае - ложное срабатывание
            if (Math.abs((detector.getCurrentSpan() - detector.getPreviousSpan())) > sensitivity) {
                final float scale = detector.getScaleFactor();

                // при уменьшении я заметил что скорость масштабирования была очень быстрая,
                // поэтому я решил поделить scale на специальный коэффициент decreasingDivisor, который я тоже подобрал
                // экспериментально
                scaleFactor *= scale < normalScaleFactor ? scale / decreasingDivisor : scale;

                // ограничиваем максимальное масштабирование в 5.0 и минимальное в 0.05
                scaleFactor = Math.max(minScaleFactor, Math.min(scaleFactor, maxScaleFactor));

                invalidate();
            }
            return true;
        }
    }
}
