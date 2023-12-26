package com.example.mytest2023.module.mvvm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.mytest2023.R;
import com.example.mytest2023.base.BaseActivity;
import com.example.mytest2023.databinding.ActivityRoomMvvmBinding;
import com.example.mytest2023.helper.ToastUtil;
import com.example.mytest2023.room.bean.Student;
import com.example.mytest2023.room.helper.DBInstance;
import com.example.mytest2023.room.helper.OnDaoInsertListener;
import com.example.mytest2023.room.helper.OnDaoFindsListener;
import com.example.mytest2023.room.dao.StudentDao;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RoomMvvmActivity extends BaseActivity {
    private static final String TAG = "RoomMvvmActivity";

    private ActivityRoomMvvmBinding binding;
    private ObservableField<String> jiandanField = new ObservableField<>();
    private RoomMvvmBean bean = new RoomMvvmBean();

    private MutableLiveData<String> liveData = new MutableLiveData<>();


    private RoomMvvmViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_room_mvvm);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_mvvm);
        binding.setListener(new Listener());

        //1、简单单向ObservableField
        jiandanField.set("简单的1");
        binding.setJiandanField2(jiandanField); //xml不是string 所以不用jiandanField.get()
        jiandanField.set("简单的2");

        //2、实体类有ObservableField
        binding.setRoomMvvmBean(bean);

        //3、MutableLiveData 数据改变的监听 有生命周期
        binding.setLiveData(liveData.getValue());
        liveData.observe(this, new Observer<String>() {
            //titleName的数据发生变化了 ，通过binding改变xml控件数据
            @Override
            public void onChanged(String s) {
//        或者        binding.setTitleName(s);
//                binding.setLiveData(s);
                binding.txtTitle.setText(s);
                ToastUtil.showMsg(RoomMvvmActivity.this, s);
            }
        });

        //4、viewModel 里面有MutableLiveData
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(RoomMvvmViewModel.class);
        binding.setRoomMvvmViewModel(viewModel);

        addObserve();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //简单的ObservableField
        jiandanField.set("简单的");
        //实体类有ObservableField
        bean.name.set("我是实体类有ObservableField");

        //重要onStart与onResume时，LiveData处于活动状态，
        // 当生命周期处于onStop或者onPause时，不回调数据更新，直至生命周期为onResume时，立即回调；
        // 当生命周期处于onDestory时，观察者会自动删除，防止内存溢出

//        liveData.postValue("单独LiveData使用");
        liveData.setValue("单独LiveData使用");

        //viewModel 里面有MutableLiveData
        viewModel.getData().postValue("ViewModel配合LiveData使用");

        //setValue()必须从主线程调用方法.但是如果需要从后台线程设置一个值,则postValue()应该使用它.
        //如果您在主线程中并使用 postValue ，则与 setValue 相比，它会花费更多毫秒来分派数据。使用 setValue 它是即时的

        //setValue()设置值。如果有活动的观察者，那么值将分派给他们。必须从主线程调用此方法。
        //postValue 如果需要从后台线程设置值，则可以使用 postValue(Object)
    }

    public class Listener {

//1、Completable：只有onComplete和onError方法，即只有“完成”和“错误”两种状态，不会返回具体的结果。
//2、Single：其回调为onSuccess和onError，查询成功会在onSuccess中返回结果，需要注意的是，如果未查询到结果，即查询结果为空，会直接走onError
// 回调，抛出EmptyResultSetException异常。
//3、Maybe：其回调为onSuccess，onError，onComplete，查询成功，如果有数据，会先回调onSuccess再回调onComplete
// ，如果没有数据，则会直接回调onComplete。
//4、Flowable/Observable：这是返回一个可观察的对象，查询的部分有变化时，都会回调它的onNext方法，没有数据变化的话，不回调。直到Rx流断开。


        @SuppressLint("CheckResult")
        public void OnClick1() {
            //可以不写线程
            StudentDao dao = DBInstance.getDBMaster().studentDao();
            DBInstance.daoFinds(dao.findAll2(),
                    new OnDaoFindsListener<List<Student>>() {
                        @Override
                        public void onNext(List<Student> students) {
                            Log.e(TAG, "OnClick1: " + students.toString());
                        }
                    });


        }

        public void OnClick2() {
            //这样写 线程报错
            StudentDao dao = DBInstance.getDBMaster().studentDao();
            Log.e(TAG, "OnClick2: " + dao.findAllByIds(new int[]{0, 1, 2}).toString());
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            }).start();

        }

        public void OnClick3() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StudentDao dao = DBInstance.getDBMaster().studentDao();
                    Log.e(TAG,
                            "OnClick3: " + dao.findLikeFirstName("钟1", "文祥1").toString());
                }
            }).start();

        }

        public void OnClick4() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StudentDao dao = DBInstance.getDBMaster().studentDao();
                    Log.e(TAG, "OnClick4: " + dao.insert(new Student("钟0", "文祥0")));
                }
            }).start();
        }

        public void OnClick5() {

            DBInstance.doInsert(new OnDaoInsertListener() {
                @Override
                public List<Long> call() {
//                    return DBInstance.getDBMaster().studentDao().insertAll(new Student[]{new
//                    Student(
//                            "钟1", "文祥1"), new Student("钟2", "文祥2")});

                    List<Student> list = new ArrayList<>();
                    list.add(new Student("钟1", "文祥1"));
                    list.add(new Student("钟2", "文祥2"));
                    return DBInstance.getDBMaster().studentDao().insertAll2(list);
                }

                @Override
                public void onNext(List<Long> longs) {
                    Log.e(TAG, "OnClick5: " + longs);
                }
            });

        }

        public void OnClick6() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StudentDao dao = DBInstance.getDBMaster().studentDao();
                    Log.e(TAG, "OnClick6: " + dao.update(new Student(0, "钟3", "文祥3")));
                }
            }).start();

        }

        public void OnClick7() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StudentDao dao = DBInstance.getDBMaster().studentDao();
                    Log.e(TAG, "OnClick7: " + dao.delete(new Student(0)));
                }
            }).start();

        }
    }


    private void addObserve() {

    }

}