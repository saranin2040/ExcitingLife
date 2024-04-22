package com.example.excitinglife;


import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.excitinglife.BusinessLogic.Task.TaskDo;
import com.example.excitinglife.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskDo> taskList;

    public TaskAdapter(List<TaskDo> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskDo task = taskList.get(position);
        holder.taskName.setText(task.getName());
        holder.taskRewardButton.setText(String.valueOf(task.getReward()));

        // Проверка, была ли анимация уже применена к этому элементу
        if (!task.isAnimated()) {
            // Применение анимации
            holder.itemView.setAlpha(0);
            holder.itemView.setTranslationY(400f);
            holder.itemView.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(position * 200)
                    .setDuration(500)
                    .start();
            // Установка флага, указывающего, что анимация была применена
            task.setAnimated(true);
        } else {
            // Если анимация уже была применена, сбрасываем ее для корректного отображения элемента
            holder.itemView.setAlpha(1f);
            holder.itemView.setTranslationY(0f);
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        Button taskRewardButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskNameTextView);
            taskRewardButton = itemView.findViewById(R.id.taskRewardButton);

            // Устанавливаем обработчик нажатия на кнопку
            taskRewardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("saranin","click");
                    // Вызывайте здесь вашу бизнес-логику или обработчик нажатия
                    int position = getAdapterPosition(); // Получаем позицию в адаптере
                    if (position != RecyclerView.NO_POSITION) {
                        // Взаимодействие с элементом списка по этой позиции
                    }
                }
            });
        }
    }

    public void animateToPosition(final int position, RecyclerView recyclerView) {
        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
        if (viewHolder != null && viewHolder.itemView != null) {
            // Начальное положение элемента снизу экрана
            viewHolder.itemView.setTranslationY(400f); // Замените 1000f на значение за пределами видимой области экрана
            // Начальная прозрачность элемента
            viewHolder.itemView.setAlpha(0f);

            // Анимация для увеличения прозрачности и поднятия вверх
            viewHolder.itemView.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(position * 200) // Задержка на основе позиции элемента
                    .setDuration(500)
                    .start();
        }
    }
}