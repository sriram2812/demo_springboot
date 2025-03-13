package com.vehicle.onboard.repository.spec;

import com.vehicle.onboard.entity.Vehicle;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleSpecificationTest {

    @Mock
    private Root<Vehicle> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Test
    void searchVehicles_AllParameters() {
        // Given
        String registrationNumber = "12345678";
        String make = "Toyota";
        String model = "Camry";
        Integer year = 2024;
        String color = "Black";
        Boolean isActive = true;

        Path<String> stringPath = mock(Path.class);
        Path<Integer> integerPath = mock(Path.class);
        Path<Boolean> booleanPath = mock(Path.class);
        Expression<String> stringExpression = mock(Expression.class);
        Predicate predicate = mock(Predicate.class);

        when(root.<String>get("registrationNumber")).thenReturn(stringPath);
        when(root.<String>get("make")).thenReturn(stringPath);
        when(root.<String>get("model")).thenReturn(stringPath);
        when(root.<Integer>get("year")).thenReturn(integerPath);
        when(root.<String>get("color")).thenReturn(stringPath);
        when(root.<Boolean>get("isActive")).thenReturn(booleanPath);

        when(criteriaBuilder.lower(any(Expression.class))).thenReturn(stringExpression);
        when(criteriaBuilder.like(any(Expression.class), anyString())).thenReturn(predicate);
        when(criteriaBuilder.equal(integerPath, year)).thenReturn(predicate);
        when(criteriaBuilder.equal(booleanPath, isActive)).thenReturn(predicate);

        // When
        Specification<Vehicle> spec = VehicleSpecification.searchVehicles(
                registrationNumber, make, model, year, color, isActive
        );
        spec.toPredicate(root, query, criteriaBuilder);

        // Then
        verify(criteriaBuilder, times(4)).like(any(Expression.class), anyString());
        verify(criteriaBuilder).equal(integerPath, year);
        verify(criteriaBuilder).equal(booleanPath, isActive);
    }

    @Test
    void searchVehicles_NullParameters() {
        // When
        Specification<Vehicle> spec = VehicleSpecification.searchVehicles(
                null, null, null, null, null, null
        );
        spec.toPredicate(root, query, criteriaBuilder);

        // Then
        verify(criteriaBuilder, never()).like(any(Expression.class), anyString());
        verify(criteriaBuilder, never()).equal(any(), any());
    }

    @Test
    void searchVehicles_EmptyStrings() {
        // When
        Specification<Vehicle> spec = VehicleSpecification.searchVehicles(
                "", "", "", null, "", null
        );
        spec.toPredicate(root, query, criteriaBuilder);

        // Then
        verify(criteriaBuilder, never()).like(any(Expression.class), anyString());
        verify(criteriaBuilder, never()).equal(any(), any());
    }
}