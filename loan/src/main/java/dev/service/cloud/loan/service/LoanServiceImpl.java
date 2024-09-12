package dev.service.cloud.loan.service;

import dev.service.cloud.loan.dto.response.LoanProductResponseDto;
import dev.service.cloud.loan.model.LoanProduct;
import dev.service.cloud.loan.repository.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{

    private final LoanProductRepository loanProductRepository;

    public List<LoanProductResponseDto> loansearching(){
        List<LoanProduct> loanProducts = loanProductRepository.findAll();
        List<LoanProductResponseDto> loanProductResponseDtos =loanProducts.stream().map((loanProduct) -> LoanProductResponseDto.from(loanProduct)).collect(Collectors.toList());

        return loanProductResponseDtos;
    }
    @Override
    public List<LoanProductResponseDto> loansearching_asc(String data) {
        if (data == null) {
            throw new IllegalArgumentException("정렬하려는 기준데이터값이 입력되지 않았습니다. url을 확인해주세요.");
        }
        // 페이지 당 자료 전달 코드
//        PageRequest pageRequest = PageRequest.of(0,6, Sort.by(data).ascending());
//        Page<LoanProduct> loanProducts = loanProductRepository.findAll(pageRequest);

        //한번에 정렬된 모든자료 전달 코드
        Sort sort = Sort.by(data).ascending();
        List<LoanProduct> loanProducts = loanProductRepository.findAll(sort);
        List<LoanProductResponseDto> loanProductResponseDtos =loanProducts.stream().map((loanProduct) -> LoanProductResponseDto.from(loanProduct)).collect(Collectors.toList());
        return loanProductResponseDtos;
    }

    @Override
    public List<LoanProductResponseDto> loansearching_desc(String data) {
        if (data == null) {
            throw new IllegalArgumentException("정렬하려는 기준데이터값이 입력되지 않았습니다. url을 확인해주세요.");
        }
        // 페이지 당 자료 전달 코드
//        PageRequest pageRequest = PageRequest.of(0,100, Sort.by(data));
//        Page<LoanProduct> loanProducts = loanProductRepository.findAll(pageRequest);

        //한번에 정렬된 모든자료 전달 코드
        Sort sort = Sort.by(data).descending();
        List<LoanProduct> loanProducts = loanProductRepository.findAll(sort);
        List<LoanProductResponseDto> loanProductResponseDtos =loanProducts.stream().map((loanProduct) -> LoanProductResponseDto.from(loanProduct)).collect(Collectors.toList());
        System.out.println("loanProductResponseDtos: " + loanProductResponseDtos);
        return loanProductResponseDtos;
    }
}

