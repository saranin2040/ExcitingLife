package com.example.excitinglife.Controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.excitinglife.BusinessLogic.Task.TaskDo;
import com.example.excitinglife.R;
import com.example.excitinglife.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondScreenFragment extends Fragment {

    private TextView numberTextView;
    private Button earnButton;
    private Button buyButton;
    private RecyclerView taskRecyclerView;

    public SecondScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.screen_second, container, false);

        numberTextView = view.findViewById(R.id.numberTextView);
        earnButton = view.findViewById(R.id.earnButton);
        buyButton = view.findViewById(R.id.buyButton);
        taskRecyclerView = view.findViewById(R.id.taskRecyclerView);

        // Установка LayoutManager для RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        taskRecyclerView.setLayoutManager(layoutManager);

        // Инициализация адаптера с данными
        List<TaskDo> tasks = getTasks();  // Предполагается, что у вас есть метод getTasks()
        TaskAdapter adapter = new TaskAdapter(tasks);
        taskRecyclerView.setAdapter(adapter);

        taskRecyclerView.setVisibility(View.GONE); // Изначально RecyclerView будет скрыт

//        taskRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                // Можно проверить, достиг ли пользователь конца списка и загрузить больше данных
//                if (!recyclerView.canScrollVertically(1)) { // 1 для проверки возможности прокрутки вниз
//                    // Загрузить больше данных, если есть
//                }
//            }
//        });

        earnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyButton.setVisibility(View.GONE);
                earnButton.setVisibility(View.GONE); // Скрыть кнопку "Купить"
                 // Запуск анимации текста
                //taskRecyclerView.setVisibility(View.VISIBLE);

                animateTextView(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        // После завершения анимации текста, показать RecyclerView
                        taskRecyclerView.setVisibility(View.VISIBLE);
                        animateItems();
                    }
                });

                //animateTextView();
                //animateItems();// Показать RecyclerView
            }
        });

        return view;
    }

    private void animateTextView(Animator.AnimatorListener listener) {
        // Создание анимации для уменьшения размера текста
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(numberTextView, "scaleX", 0.43f); // Подгонка под 20sp из 156sp
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(numberTextView, "scaleY", 0.43f);
        scaleX.setDuration(700);
        scaleY.setDuration(700);

        // Перемещение TextView вверх
        ObjectAnimator moveUp = ObjectAnimator.ofFloat(numberTextView, "translationY", -600f); // Выберите подходящее значение в зависимости от макета
        moveUp.setDuration(700);

        //ObjectAnimator moveUp2 = ObjectAnimator.ofFloat(taskRecyclerView, "translationY", -700f); // Выберите подходящее значение в зависимости от макета
        //moveUp2.setDuration(0);

        // Аниматоры можно запускать одновременно с помощью AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, moveUp);
        animatorSet.addListener(listener);
        //animatorSet.playTogether(scaleX, scaleY, moveUp,moveUp2);
        animatorSet.start();
    }

    private void animateItems() {
        // Предполагаем, что taskRecyclerView уже инициализирован и у него есть адаптер с данными.
        final int itemCount = taskRecyclerView.getAdapter().getItemCount();
        for (int i = 0; i < itemCount; ++i) {
            final int position = i;
            taskRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((TaskAdapter) taskRecyclerView.getAdapter()).animateToPosition(position, taskRecyclerView);
                }
            }, i * 100); // Задержка для последовательного эффекта появления элементов
        }
    }

    private List<TaskDo> getTasks() {
        // Здесь должна быть логика получения данных о задачах, например, из модели или базы данных
        List<TaskDo> tasks = new ArrayList<>();
        tasks.add(new TaskDo("Task 1", 100));
        tasks.add(new TaskDo("Task 2", 200));
        tasks.add(new TaskDo("Task 3", 200));
        tasks.add(new TaskDo("Task 4", 200));
        tasks.add(new TaskDo("Task 5", 200));
        tasks.add(new TaskDo("Task 6", 200));
        tasks.add(new TaskDo("Task 7", 200));
        tasks.add(new TaskDo("Task 8", 200));
        tasks.add(new TaskDo("Task 7", 200));
        tasks.add(new TaskDo("Task 8", 200));
        // Добавь остальные задачи
        return tasks;
    }
}
