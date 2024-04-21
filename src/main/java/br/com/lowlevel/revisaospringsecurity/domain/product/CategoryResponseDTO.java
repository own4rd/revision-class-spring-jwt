package br.com.lowlevel.revisaospringsecurity.domain.product;


public record CategoryResponseDTO(String id, String name) {
    public CategoryResponseDTO(Category category){
        this(category.getId(), category.getName());
    }
}