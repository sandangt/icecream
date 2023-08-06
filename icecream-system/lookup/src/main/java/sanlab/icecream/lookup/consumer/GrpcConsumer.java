package sanlab.icecream.lookup.consumer;

import java.util.List;
import java.util.UUID;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;
import sanlab.icecream.lookup.service.MediaService;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.LookupServiceGrpc;
import sanlab.icecream.sharedlib.proto.MediaCollectionResponse;
import sanlab.icecream.sharedlib.proto.MediaDTO;
import sanlab.icecream.sharedlib.proto.MediaRequest;
import sanlab.icecream.sharedlib.proto.MediaResponse;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;


@GRpcService
@RequiredArgsConstructor
public class GrpcConsumer extends LookupServiceGrpc.LookupServiceImplBase {
    private final MediaService mediaService;

    @Override
    public void getMediaById(MediaRequest request, StreamObserver<MediaResponse> responseObserver) {
        try {
            MediaDTO media = mediaService.getMediaById(UUID.fromString(request.getMediaId()));
            MediaResponse response = MediaResponse.newBuilder().setMedia(media).build();
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getAllMedia(PageInfoRequest request, StreamObserver<MediaCollectionResponse> responseObserver) {
        List<MediaDTO> mediaList = mediaService.getAllMedia(request);
        MediaCollectionResponse response = MediaCollectionResponse.newBuilder()
            .addAllMediaCollection(mediaList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
