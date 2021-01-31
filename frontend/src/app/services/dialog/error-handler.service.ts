import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ErrorDialogComponent} from '../../components/dialog/error-dialog/error-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  public dialogConfig;

  constructor(private router: Router, private dialog: MatDialog) {
    this.dialogConfig = {
      height: '200px',
      width: '400px',
      disableClose: true,
      data: { }
    };
  }

  public openErrorDialog(errorMessage: string): MatDialogRef<any> {
    this.dialogConfig.data = { errorMessage };
    return this.dialog.open(ErrorDialogComponent, this.dialogConfig);
  }
}
