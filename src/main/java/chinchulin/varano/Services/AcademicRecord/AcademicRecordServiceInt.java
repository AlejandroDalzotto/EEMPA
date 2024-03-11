package chinchulin.varano.Services.AcademicRecord;

import chinchulin.varano.Payloads.DTO.AcademicRecordDTO;
import chinchulin.varano.Payloads.Request.AcademicRecordRequest;

import java.util.List;

public interface AcademicRecordServiceInt {

    AcademicRecordDTO getOneByCode(String code);

    List<AcademicRecordDTO> getAllByTerm(String term, Integer limit, Integer offset);

    List<AcademicRecordDTO> getAllByBlankTerm(Integer limit, Integer offset);

    Short getTotalPagesByTerm(String query);

    Short getTotalPagesByBlankTerm();

    AcademicRecordDTO create(AcademicRecordRequest newEntry);

    AcademicRecordDTO update(String code, AcademicRecordRequest newAttributes);
}
