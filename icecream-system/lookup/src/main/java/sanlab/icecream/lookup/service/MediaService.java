package sanlab.icecream.lookup.service;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sanlab.icecream.lookup.exception.ErrorCode;
import sanlab.icecream.lookup.mapper.IMapper;
import sanlab.icecream.lookup.model.Media;
import sanlab.icecream.lookup.repository.IMediaRepository;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.MediaDTO;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;


@Service
@RequiredArgsConstructor
public class MediaService {
    private final IMediaRepository mediaRepository;
    private final IMapper mapper;

    public List<MediaDTO> getAllMedia(PageInfoRequest pageInfo) {
        Pageable page = PageRequest.of(
            Math.round((float) pageInfo.getOffset() / (float) pageInfo.getLimit()),
            pageInfo.getLimit()
        );
        return mediaRepository.findAllByOrderByCreatedOnDesc(page)
            .stream()
            .map(mapper.INSTANCE::modelToDTO)
            .toList();
    }

    public MediaDTO getMediaById(UUID id) {
        Media media = mediaRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Product with id: %s not found", id), ErrorCode.MEDIA_NOT_FOUND
            ));
        return mapper.INSTANCE.modelToDTO(media);
    }
}
