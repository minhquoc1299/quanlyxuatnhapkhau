package com.mini_project.CoreModule.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_ref_id")
public class RefEntity {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "id_export", columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    private UUID idExport;

    @Column(name = "ref_id_import", columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    private UUID refIdImport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id",insertable = false,updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private DetailsImportExportEntity idExportRefEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_import_export",insertable = false,updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private DetailsImportExportEntity refIdExportRefEntity;
}
