package com.baoge.dao;

import com.baoge.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JobDao {
    List<Job> getJobPlaceAndType(@Param("place") String place, @Param("type") String type);

    Job getJobInfoByid(int id);

    List<Job> getJobsBynews();

    List<Job> getallJob(Integer userid);

    List<Job> getJobByUserAndstatus(@Param("userid")Integer userid,@Param("i") int i);

    Integer releaseJob(@Param("job")Job job,@Param("user_id")Integer user_id);

    Integer editJob(Job job);

    Integer deleteJobById(int id);

    List<Job> allJobAct();

    List<Job> searchJob(@Param("uname")String uname,@Param("name") String name,@Param("status") Integer status);

    Integer setStatus(@Param("id") int id,@Param("i") int i);

    Job getJobInfoByid2(Integer id);
}
