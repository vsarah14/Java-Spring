package com.example.assignment2.service;

import com.example.assignment2.model.Assignment;
import com.example.assignment2.model.Attendance;
import com.example.assignment2.model.Laboratory;
import com.example.assignment2.model.Teacher;
import com.example.assignment2.model.dto.LaboratoryDTO;
import com.example.assignment2.repository.AssignmentRepository;
import com.example.assignment2.repository.LaboratoryRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Builder
@Data
public class LaboratoryService {

    @Autowired
    private final LaboratoryRepository laboratoryRepository;
    private TeacherService teacherService;
    private AttendanceService attendanceService;
    private AssignmentRepository assignmentRepository;

    public Laboratory toEntity(LaboratoryDTO lab) {

        Teacher teacher = teacherService.findById(lab.getTeacher_id());
        List<Attendance> attendance = attendanceService.findAllByIds(lab.getAttendance_id());

        return Laboratory.builder()
                .laboratoryId(lab.getLaboratoryId())
                .number(lab.getNumber())
                .title(lab.getTitle())
                .description(lab.getDescription())
                .date(lab.getDate())
                .teacherId(teacher)
                .attendances(attendance)
                .build();
    }

    public LaboratoryDTO fromEntity(Laboratory lab) {

        List<Integer> list = new ArrayList<>();
        for (Attendance a : lab.getAttendances()) {
            list.add(a.getId());
        }
        return LaboratoryDTO.builder()
                .laboratoryId(lab.getLaboratoryId())
                .number(lab.getNumber())
                .title(lab.getTitle())
                .description(lab.getDescription())
                .date(lab.getDate())
                .teacher_id(lab.getTeacherId().getTeacherId())
                .attendance_id(list)
                .build();
    }

    //C - create operation
    public Laboratory createLaboratory(LaboratoryDTO laboratory) {
        Laboratory lab = toEntity(laboratory);
        return laboratoryRepository.save(lab);
    }

    //R - read operation
    public List<Laboratory> readLaboratory() {
        return laboratoryRepository.findAll();
    }

    //U - update operation
    public Laboratory updateLaboratory(LaboratoryDTO laboratory, Integer laboratoryId) {
        Laboratory l = laboratoryRepository.findById(laboratoryId).get();
        LaboratoryDTO lab = fromEntity(l);

        if (Objects.nonNull(laboratory.getNumber()) && !"".equalsIgnoreCase(Integer.toString(laboratory.getNumber()))) {
            lab.setNumber(laboratory.getNumber());
        }

        if (Objects.nonNull(laboratory.getDate()) && !"".equalsIgnoreCase(laboratory.getDate())) {
            lab.setDate(laboratory.getDate());
        }

        if (Objects.nonNull(laboratory.getTitle()) && !"".equalsIgnoreCase(laboratory.getTitle())) {
            lab.setTitle(laboratory.getTitle());
        }

        if (Objects.nonNull(laboratory.getDescription()) && !"".equalsIgnoreCase(laboratory.getDescription())) {
            lab.setDescription(laboratory.getDescription());
        }

        if (Objects.nonNull(laboratory.getTeacher_id()) && !"".equalsIgnoreCase(laboratory.getTeacher_id().toString())) {
            lab.setTeacher_id(laboratory.getTeacher_id());
        }

        if (Objects.nonNull(laboratory.getAttendance_id()) && !"".equalsIgnoreCase(laboratory.getAttendance_id().toString())) {
            lab.setAttendance_id(laboratory.getAttendance_id());
        }

        return laboratoryRepository.save(toEntity(lab));
    }

    //D - delete operation
    public String deleteLaboratory(Integer laboratoryId) {
        Laboratory l = laboratoryRepository.findById(laboratoryId).get();
        List<Assignment> assignment = assignmentRepository.findAll();
        for (Assignment a : assignment)
            if (a.getLaboratory().equals(l)) {
                return "Laboratory couldn't be deleted.";
            }
        laboratoryRepository.deleteById(laboratoryId);
        return "Laboratory deleted successfully.";
    }

    public Laboratory findById(int id) {

        var val = laboratoryRepository.findById(id);

        if (val.isPresent()) {
            Laboratory lab = val.get();
            return lab;
        } else return null;
    }

}
