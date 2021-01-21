import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {EnrollmentService} from '../../../services/enrollment/enrollment.service';
import {MatTableDataSource} from '@angular/material/table';
import {Course} from '../../../models/enrollment/course';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-enrollment',
  templateUrl: './enrollment.component.html',
  styleUrls: ['./enrollment.component.css']
})
export class EnrollmentComponent implements OnInit, AfterViewInit {
  blocks: string[];
  public displayedColumns = ['courseName', 'courseCode', 'courseType', 'numberOfEcts', 'isSelectable'];
  public dataSource = new MatTableDataSource<Course>();

  @ViewChild(MatSort) sort: MatSort;

  constructor(private enrollmentService: EnrollmentService) { }

  ngOnInit(): void {
    this.enrollmentService.getEnrollmentBlocks('238123').subscribe(
      res => {
        this.blocks = res.enrollmentBlockNames;
      }
    );
    this.dataSource.data = [
      {
        courseName: 'Nazwa',
        courseCode: 'aa',
        courseType: 'aa',
        numberOfEcts: 23,
        isSelectable: true
      }
    ];
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

}
