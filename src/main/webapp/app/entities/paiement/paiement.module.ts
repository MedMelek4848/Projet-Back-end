import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PaiementComponent } from './list/paiement.component';
import { PaiementDetailComponent } from './detail/paiement-detail.component';
import { PaiementUpdateComponent } from './update/paiement-update.component';
import { PaiementDeleteDialogComponent } from './delete/paiement-delete-dialog.component';
import { PaiementRoutingModule } from './route/paiement-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [SharedModule, PaiementRoutingModule,FormsModule,ReactiveFormsModule],
  declarations: [PaiementComponent, PaiementDetailComponent, PaiementUpdateComponent, PaiementDeleteDialogComponent],
})
export class PaiementModule {}
