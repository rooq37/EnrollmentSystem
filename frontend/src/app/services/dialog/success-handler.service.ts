import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ErrorDialogComponent} from '../../components/dialog/error-dialog/error-dialog.component';
import {SuccessDialogComponent} from '../../components/dialog/success-dialog/success-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class SuccessHandlerService {

  public dialogConfig;

  constructor(private router: Router, private dialog: MatDialog) {
    this.dialogConfig = {
      height: '200px',
      width: '400px',
      disableClose: true,
      data: { }
    };
  }

  public openSuccessDialog(successMessage: string): MatDialogRef<any> {
    this.dialogConfig.data = { successMessage };
    return this.dialog.open(SuccessDialogComponent, this.dialogConfig);
  }
}
