import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INote, NewNote } from '../note.model';

export type PartialUpdateNote = Partial<INote> & Pick<INote, 'id'>;

export type EntityResponseType = HttpResponse<INote>;
export type EntityArrayResponseType = HttpResponse<INote[]>;

@Injectable({ providedIn: 'root' })
export class NoteService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/notes');

  constructor(protected httpClient: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(note: NewNote): Observable<EntityResponseType> {
    return this.httpClient.post<INote>(this.resourceUrl, note, { observe: 'response' });
  }

  update(note: INote): Observable<EntityResponseType> {
    return this.httpClient.put<INote>(`${this.resourceUrl}/${this.getNoteIdentifier(note)}`, note, { observe: 'response' });
  }

  partialUpdate(note: PartialUpdateNote): Observable<EntityResponseType> {
    return this.httpClient.patch<INote>(`${this.resourceUrl}/${this.getNoteIdentifier(note)}`, note, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.httpClient.get<INote>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.httpClient.get<INote[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.httpClient.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getNoteIdentifier(note: Pick<INote, 'id'>): number {
    return note.id;
  }

  compareNote(o1: Pick<INote, 'id'> | null, o2: Pick<INote, 'id'> | null): boolean {
    return o1 && o2 ? this.getNoteIdentifier(o1) === this.getNoteIdentifier(o2) : o1 === o2;
  }


  addNoteToCollectionIfMissing<Type extends Pick<INote, 'id'>>(
    noteCollection: Type[],
    ...notesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const notes: Type[] = notesToCheck.filter(isPresent);
    if (notes.length > 0) {
      const noteCollectionIdentifiers = noteCollection.map(noteItem => this.getNoteIdentifier(noteItem)!);
      const notesToAdd = notes.filter(noteItem => {
        const noteIdentifier = this.getNoteIdentifier(noteItem);
        if (noteCollectionIdentifiers.includes(noteIdentifier)) {
          return false;
        }
        noteCollectionIdentifiers.push(noteIdentifier);
        return true;
      });
      return [...notesToAdd, ...noteCollection];
    }
    return noteCollection;
  }
  
  searchNotes(matricule: string): Observable<any> {
    // Effectuer la requête HTTP pour récupérer les paiements correspondants à la matricule donnée
    // Assurez-vous d'ajuster l'URL de l'API appropriée selon votre configuration
    return this.httpClient.get<any>(`/api/notes?matriculeEleve=${matricule}`);
  }
}
