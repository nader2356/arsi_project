import { Component } from '@angular/core';
import { SkillsService } from '../services/skills.service';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent {
  categories: any = [];
  selectedCategory: any = null;
  constructor(private skillsService: SkillsService) {}

  ngOnInit(): void {
    this.displayCategory();
  }

  displayCategory() {
    this.skillsService.getCategories().subscribe((res: any) => {
      this.categories = res;
    });
  }

  editSkill(skill: any) {}

}
