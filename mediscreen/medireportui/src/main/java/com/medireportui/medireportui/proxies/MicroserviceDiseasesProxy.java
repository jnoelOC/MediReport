
package com.medireportui.medireportui.proxies;

import com.medireportui.medireportui.beans.DiseaseBean;
import com.medireportui.medireportui.beans.NoteBean;
import com.medireportui.medireportui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@FeignClient(name = "microservice-reports", url = "localhost:9390")
@FeignClient(name = "microservice-reports", url = "${SPRING.REPORT.URL}")
public interface MicroserviceDiseasesProxy {
    @GetMapping(value = "/report/getrisklevelforpatient")
    DiseaseBean.RiskLevel getRiskLevel(@RequestParam("idPatient") int idPatient);

}