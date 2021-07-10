package com.baoge.dao;

import com.baoge.entity.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ResumeDao {

    Resume getResumeByUserid(Integer userid);

    Integer editResume(Resume resume);

    Integer addResume(@Param("resume") Resume resume, @Param("userid") Integer userid);
}
