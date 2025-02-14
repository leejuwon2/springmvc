package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqAddInstructorDto;
import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.request.study.ReqUpdateMajorDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.dto.response.common.ResponseDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.repository.StudentStudyRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentStudyService {

    @Autowired
    private StudentStudyRepository studentStudyRepository;

    public SuccessResponseDto<List<Major>> getMajorsAll() throws NotFoundException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .findMajorAll()
                        .orElseThrow(() -> new NotFoundException("학과 데이터가 존재하지 않습니다.")));
    }

    public SuccessResponseDto<List<Instructor>> getInstructorsAll() throws NotFoundException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .findInstructorAll()
                        .orElseThrow(() -> new NotFoundException("교수 데이터가 존재하지 않습니다.")));
    }

    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Major> addMajor(ReqAddMajorDto reqAddMajorDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .saveMajor(new Major(0, reqAddMajorDto.getMajorName()))
                        .get()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Instructor> addInstructor(ReqAddInstructorDto reqAddInstructorDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .saveInstructor(new Instructor(0, reqAddInstructorDto.getInstructorName()))
                        .get()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Major> modifyMajor(int majorId, ReqUpdateMajorDto reqUpdateMajorDto) throws NotFoundException {
        Major modifiedMajor = studentStudyRepository
                .updateMajor(new Major(majorId, reqUpdateMajorDto.getMajorName()))
                .orElseThrow(() -> new NotFoundException("해당 학과 ID는 존재하지 않습니다."));
        return new SuccessResponseDto<>(modifiedMajor);
    }


}
