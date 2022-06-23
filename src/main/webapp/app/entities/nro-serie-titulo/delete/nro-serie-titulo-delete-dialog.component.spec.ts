jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { NroSerieTituloService } from '../service/nro-serie-titulo.service';

import { NroSerieTituloDeleteDialogComponent } from './nro-serie-titulo-delete-dialog.component';

describe('NroSerieTitulo Management Delete Component', () => {
  let comp: NroSerieTituloDeleteDialogComponent;
  let fixture: ComponentFixture<NroSerieTituloDeleteDialogComponent>;
  let service: NroSerieTituloService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [NroSerieTituloDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(NroSerieTituloDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(NroSerieTituloDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(NroSerieTituloService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
