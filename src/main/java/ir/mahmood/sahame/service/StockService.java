package ir.mahmood.sahame.service;

import ir.mahmood.sahame.dto.BuyDto;
import ir.mahmood.sahame.dto.StockDto;
import ir.mahmood.sahame.entity.BuyEntity;
import ir.mahmood.sahame.entity.StockEntity;
import ir.mahmood.sahame.entity.UserEntity;
import ir.mahmood.sahame.exception.StockNotFoundException;
import ir.mahmood.sahame.repository.BuyRepository;
import ir.mahmood.sahame.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    private StockRepository stockRepository;
    private ModelMapper modelMapper;
    private BuyRepository buyRepository;
    private JwtUserDetailsService userDetailsService;

    @Autowired
    public StockService(
            StockRepository stockRepository,
            ModelMapper modelMapper,
            BuyRepository buyRepository,
            JwtUserDetailsService userDetailsService
    ) {
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
        this.buyRepository = buyRepository;
        this.userDetailsService = userDetailsService;
    }

    public void store(StockDto stockDto) {
        stockRepository.save(modelMapper.map(stockDto, StockEntity.class));
    }

    public void bulkStore(List<StockDto> stockDtos) {
        List<StockEntity> stockEntities = stockDtos.stream().map(
                stockDto -> modelMapper.map(stockDto, StockEntity.class)
        ).collect(Collectors.toList());

        stockRepository.saveAll(stockEntities);
    }

    public List<StockDto> list() {
        return stockRepository.findAll().stream().map(
                stockEntity -> modelMapper.map(stockEntity, StockDto.class)
        ).collect(Collectors.toList());
    }

    public Page<StockDto> list(Pageable pageable, String search) {
        Page<StockEntity> stockEntities;

        if (search == null) {
            stockEntities = stockRepository.findAll(pageable);
        } else {
            stockEntities = stockRepository.findBySymbolContaining(search, pageable);
        }

        return stockEntities.map(stockEntity -> modelMapper.map(stockEntity, StockDto.class));
    }

    public BuyEntity buy(Integer stockId, BuyDto buyDto) throws StockNotFoundException {
        // fetch stockEntity from db
        Optional<StockEntity> optionalStockEntity = stockRepository.findById(stockId);
        if (optionalStockEntity.isEmpty()) {
            throw new StockNotFoundException();
        }
        StockEntity stockEntity = optionalStockEntity.get();

        // set totalPrice and buyAt in Dto
        buyDto.setTotalPrice((long) buyDto.getBuyPrice() * buyDto.getBuyCount());
        buyDto.setBuyAt(new Date());

        // set stockEntity in buyEntity
        BuyEntity buyEntity = modelMapper.map(buyDto, BuyEntity.class);
        buyEntity.setStockEntity(stockEntity);

        // set userEntity in buyEntity
        UserEntity userEntity = userDetailsService.loadUserEntityByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        buyEntity.setUserEntity(userEntity);

        // persist buyEntity
        return buyRepository.save(buyEntity);
    }
}
