package com.project.InternshipMonitoringSystem.components.mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class MarkService {
    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> getMarks() {
        return markRepository.findAll();
    }

    public void addNewMark(Mark mark) {
        System.out.println(mark);
        markRepository.save(mark);
    }

    public void deleteMark(Long markId) {
        boolean exists = markRepository.existsById(markId);
        if (!exists) {
            throw new IllegalStateException("Mark with ID " +markId + " does not exist.");
        }
        markRepository.deleteById(markId);
    }

    @Transactional
    public void updateMark(Long markId, int value) {
        Mark mark = markRepository.findById(markId)
                .orElseThrow(() -> new IllegalStateException("Mark with ID " + markId + " does not exist."));
        if (value > 0 &&
                !Objects.equals(mark.getValue(), value)) {
            mark.setValue(value);
        }
    }
}
