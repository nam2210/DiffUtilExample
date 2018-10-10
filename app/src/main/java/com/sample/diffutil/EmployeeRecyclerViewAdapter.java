package com.sample.diffutil;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class EmployeeRecyclerViewAdapter extends
                                         RecyclerView.Adapter<EmployeeRecyclerViewAdapter
                                                 .ViewHolder> {

    private List<Employee> mEmployees = new ArrayList<>();

    public EmployeeRecyclerViewAdapter(List<Employee> employeeList) {
        this.mEmployees.addAll(employeeList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Employee employee = mEmployees.get(position);
        holder.name.setText(employee.getName());
        holder.role.setText(employee.getRole());

    }



    public void updateEmployeeListItems(List<Employee> employees) {
        final EmployeeDiffCallback diffCallback = new EmployeeDiffCallback(this.mEmployees, employees);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mEmployees.clear();
        this.mEmployees.addAll(employees);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public void addNewItems(List<Employee> addEmployeeList) {
        List<Employee> news = new ArrayList<>(mEmployees);
        news.addAll(addEmployeeList);

        final EmployeeDiffCallback diffCallback = new EmployeeDiffCallback(this.mEmployees, news);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mEmployees.clear();
        this.mEmployees.addAll(news);
        diffResult.dispatchUpdatesTo(this);
    }

    public void removeItems(List<Employee> employees){
        List<Employee> news = new ArrayList<>(mEmployees);
        news.removeAll(employees);

        final EmployeeDiffCallback diffCallback = new EmployeeDiffCallback(this.mEmployees, news);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mEmployees.clear();
        this.mEmployees.addAll(news);
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateItem(Employee employee){
        List<Employee> news = new ArrayList<>(mEmployees);
        int index = news.indexOf(employee);
        news.set(index, employee);

        final EmployeeDiffCallback diffCallback = new EmployeeDiffCallback(this.mEmployees, news);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mEmployees.clear();
        this.mEmployees.addAll(news);
        diffResult.dispatchUpdatesTo(this);

    }

    //add
    //remove
    //update
    //click

    private static final String TAG = EmployeeRecyclerViewAdapter.class.getSimpleName();

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView role;
        private final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.employee_name);
            role = (TextView) itemView.findViewById(R.id.employee_role);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.e(TAG,"position=" + mEmployees.get(position).name);
                }
            });
        }


    }
}
