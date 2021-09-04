import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Observable, of } from "rxjs";

export class MockedInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if(req.url.endsWith('api/mockcategory')){
            return of(new HttpResponse({ status: 200, body: this.mockDropdown() }));
        }
        if(req.url.endsWith('api/mockgriddatasource')){
            return of(new HttpResponse({ status: 200, body: this.mockGridDataSource() }));
        }
        return next.handle(req);
    }

    mockDropdown(){
        return  {
            "status": "Success",
            "data": [
                { key: "Key 1", value: 'Value 1'},
                { key: "Key 2", value: 'Value 2'},
                { key: "Key 3", value: 'Value 3'},
                { key: "Key 4", value: 'Value 4'},
                { key: "Key 5", value: 'Value 5'},
            ]
        }
    }

    mockGridDataSource(){
        return  {
            "status": "Success",
            "data": [
                { "totalcount": 21, "facilityId": 62, "client": "Lancaster Specialty Surgery Center, LLC", "name": "Newton, Kristina", "dob": null, "dateOfSurgery": "04/30/2020", "insuranceProvider": "Aetna", "physician": "Priano, Steven", "primartCPT": "24359", "mrn": "4979", "codingReturned": 0, "opReport": 0, "pathology": 0, "pricing": 1, "high$$$": 0, "nsnHigh$$$": 1, "block": 0, "coding": 0, "inCoding": 0, "variance": 0, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 1, "bdosid": 657274, "timeCol": 474, "oldCase": 1, "queueName": "OtherMisc", "approved": 0, "lastNote": null, "returnedFor": "" },
                { "totalcount": 21, "facilityId": 62, "client": "Lancaster Specialty Surgery Center, LLC", "name": "Robertson, Keren", "dob": null, "dateOfSurgery": "05/08/2020", "insuranceProvider": "CareWorks W/C", "physician": "D'Onofrio, Mark", "primartCPT": "25609", "mrn": "6076", "codingReturned": 0, "opReport": 0, "pathology": 0, "pricing": 0, "high$$$": 0, "nsnHigh$$$": 0, "block": 0, "coding": 0, "inCoding": 0, "variance": 0, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 0, "bdosid": 661334, "timeCol": 463, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": "Back on RFI-pending C9", "returnedFor": "" }, 
                { "totalcount": 21, "facilityId": 15, "client": "Hagerstown Surgery Center", "name": "YOUNT , RUDY", "dob": null, "dateOfSurgery": "05/19/2020", "insuranceProvider": "MEDICARE", "physician": "SHERMAN, GARY", "primartCPT": "64708", "mrn": "19199", "codingReturned": 0, "opReport": 0, "pathology": 0, "pricing": 0, "high$$$": 0, "nsnHigh$$$": 0, "block": 0, "coding": 0, "inCoding": 0, "variance": 0, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 0, "bdosid": 668694, "timeCol": 64, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": "testing mis reqiuest", "returnedFor": "" }, 
                { "totalcount": 21, "facilityId": 62, "client": "Lancaster Specialty Surgery Center, LLC", "name": "VanHorn, Thomas", "dob": null, "dateOfSurgery": "06/29/2020", "insuranceProvider": "AnthemBCBS", "physician": "Priano, Steven", "primartCPT": "29881", "mrn": "6198", "codingReturned": 0, "opReport": 0, "pathology": 0, "pricing": 0, "high$$$": 0, "nsnHigh$$$": 0, "block": 0, "coding": 0, "inCoding": 0, "variance": 0, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 0, "bdosid": 672408, "timeCol": 13, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": null, "returnedFor": "" },
                { "totalcount": 21, "facilityId": 62, "client": "Lancaster Specialty Surgery Center, LLC", "name": "Waits, Alice", "dob": null, "dateOfSurgery": "06/29/2020", "insuranceProvider": "Medicare", "physician": "Priano, Steven", "primartCPT": "26160", "mrn": "6226", "codingReturned": 0, "opReport": 0, "pathology": 0, "pricing": 0, "high$$$": 0, "nsnHigh$$$": 0, "block": 0, "coding": 0, "inCoding": 0, "variance": 0, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 0, "bdosid": 672456, "timeCol": 13, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": null, "returnedFor": "" }, 
                { "totalcount": 21, "facilityId": 15, "client": "Hagerstown Surgery Center", "name": "MOORE, NICHOLE", "dob": null, "dateOfSurgery": "06/29/2020", "insuranceProvider": "MULTI PLAN - CIGNA", "physician": "HOLOBINKO, JOSEPH", "primartCPT": "29888", "mrn": "19453", "codingReturned": 0, "opReport": 0, "pathology": 0, "pricing": 0, "high$$$": 0, "nsnHigh$$$": 0, "block": 0, "coding": 0, "inCoding": 0, "variance": 0, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 0, "bdosid": 671830, "timeCol": 64, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": null, "returnedFor": "" }, 
                { "totalcount": 21, "facilityId": 15, "client": "Hagerstown Surgery Center", "name": "NIKLEWSKI, CAROL", "dob": null, "dateOfSurgery": "06/29/2020", "insuranceProvider": "UNITED  HEALTHCARE - MEDICAID", "physician": "HOLOBINKO, JOSEPH", "primartCPT": "27130", "mrn": "19455", "codingReturned": 0, "opReport": 0, "pathology": 1, "pricing": 1, "high$$$": 0, "nsnHigh$$$": 0, "block": 0, "coding": 0, "inCoding": 0, "variance": 0, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 0, "bdosid": 671892, "timeCol": 64, "oldCase": 1, "queueName": "OtherMisc", "approved": 1, "lastNote": "Please add other documents", "returnedFor": "" }, 
                { "totalcount": 21, "facilityId": 15, "client": "Hagerstown Surgery Center", "name": "TWENTEY, JAMES", "dob": null, "dateOfSurgery": "06/30/2020", "insuranceProvider": "MEDICARE", "physician": "Souffrant, Jean Guy-Yoma", "primartCPT": "64635", "mrn": "4843", "codingReturned": 0, "opReport": 0, "pathology": 1, "pricing": 1, "high$$$": 0, "nsnHigh$$$": 1, "block": 0, "coding": 0, "inCoding": 0, "variance": 1, "authReview": 0, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 0, "otherMisc": 1, "returned": 1, "bdosid": 673627, "timeCol": 58, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": null, "returnedFor": "" }, 
                { "totalcount": 21, "facilityId": 62, "client": "Lancaster Specialty Surgery Center, LLC", "name": "Patterson, Kyren", "dob": null, "dateOfSurgery": "07/02/2020", "insuranceProvider": "", "physician": "Priano, Steven", "primartCPT": "25605", "mrn": "6394", "codingReturned": 0, "opReport": 0, "pathology": 1, "pricing": 1, "high$$$": 0, "nsnHigh$$$": 1, "block": 1, "coding": 1, "inCoding": 1, "variance": 1, "authReview": 1, "returnedCodingRFI": 0, "overdue": 0, "overdue1": 0, "codingExpert": 1, "otherMisc": 1, "returned": 1, "bdosid": 674817, "timeCol": 61, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": null, "returnedFor": "" }, 
                { "totalcount": 21, "facilityId": 15, "client": "Hagerstown Surgery Center", "name": "SUTER, CALE", "dob": null, "dateOfSurgery": "07/03/2020", "insuranceProvider": "CARE FIRST BLUE CHOICE", "physician": "SCOTT, KIRBY", "primartCPT": "69424", "mrn": "15698", "codingReturned": 0, "opReport": 0, "pathology": 1, "pricing": 1, "high$$$": 1, "nsnHigh$$$": 1, "block": 1, "coding": 1, "inCoding": 1, "variance": 1, "authReview": 1, "returnedCodingRFI": 0, "overdue": 1, "overdue1": 1, "codingExpert": 1, "otherMisc": 1, "returned": 1, "bdosid": 674958, "timeCol": 51, "oldCase": 1, "queueName": "OtherMisc", "approved": 2, "lastNote": null, "returnedFor": "" }
            ] 
        }
            
        
    }
}