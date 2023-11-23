import { Component } from '@angular/core';
import { SkillsService } from '../services/skills.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent {
  categories: any = [];
  selectedCategory: any = null;
  newSkill = '';
  newCategory = {
    name: '',
    description: '',
  };
  constructor(
    private skillsService: SkillsService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.displayCategory();
  }

  displayCategory() {
    this.skillsService.getCategories().subscribe((res: any) => {
      this.categories = res;
    });
  }

  editSkill(item: any, skill: any) {
    this.skillsService
      .editSkill({ ...skill, categoryId: item.id })
      .subscribe((res: any) => {
        this.displayCategory();
        this.messageService.add({
          severity: 'success',
          summary: 'Les compétences ont été mises à jour avec succès.',
          detail: 'Les compétences ont été mises à jour avec succès. !!!',
        });
        console.log(res);
      });
  }

  addCategory() {
    this.skillsService.addCategory(this.newCategory).subscribe((res: any) => {
      this.displayCategory();
      this.messageService.add({
        severity: 'success',
        summary: 'Catégorie enregistrée avec succès.',
        detail: 'Catégorie enregistrée avec succès. !!!',
      });
      this.newCategory = {
        name: '',
        description: '',
      };
      console.log(res);
    });
  }

  addSkill(category: any) {
    this.skillsService
      .addSkill({
        categoryId: category.id,
        description: '...',
        name: this.newSkill,
      })
      .subscribe((res: any) => {
        this.displayCategory();
        console.log(res);
        this.messageService.add({
          severity: 'success',
          summary: 'Catégorie supprimée avec succès.',
          detail: 'Catégorie supprimée avec succès!',
        });
      });
  }

  deleteSkill(skill: any) {
    this.skillsService.deleteSkill(skill.id).subscribe((res: any) => {
      this.displayCategory();
      console.log(res);
      this.messageService.add({
        severity: 'success',
        summary: 'Catégorie supprimée avec succès.',
        detail: 'Catégorie supprimée avec succès!',
      });
    });
  }

  deleteCategory(category: any) {
    this.skillsService.deleteCategory(category.id).subscribe((res: any) => {
      this.displayCategory();
      console.log(res);
      this.messageService.add({
        severity: 'success',
        summary: 'category deleted Successfully',
        detail: 'category deleted Successfully !!!',
      });
    });
  }

}
