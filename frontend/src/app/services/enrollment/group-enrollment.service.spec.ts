import { TestBed } from '@angular/core/testing';

import { GroupEnrollmentService } from './group-enrollment.service';

describe('GroupEnrollmentService', () => {
  let service: GroupEnrollmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupEnrollmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
