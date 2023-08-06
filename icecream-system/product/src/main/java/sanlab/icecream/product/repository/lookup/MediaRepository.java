package sanlab.icecream.product.repository.lookup;

import java.util.Optional;
import java.util.UUID;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sanlab.icecream.sharedlib.constant.GrpcChannel;
import sanlab.icecream.sharedlib.proto.LookupServiceGrpc;
import sanlab.icecream.sharedlib.proto.MediaDTO;
import sanlab.icecream.sharedlib.proto.MediaRequest;
import sanlab.icecream.sharedlib.proto.MediaResponse;


@Repository
public class MediaRepository {
    private final LookupServiceGrpc.LookupServiceBlockingStub stub;

    public MediaRepository(@Qualifier(GrpcChannel.LOOKUP) ManagedChannel lookupChannel) {
        this.stub = LookupServiceGrpc.newBlockingStub(lookupChannel);
    }

    public Optional<MediaDTO> getMediaById(UUID id) {
        MediaRequest request = MediaRequest.newBuilder().setMediaId(id.toString()).build();
        try {
            MediaResponse response = stub.getMediaById(request);
            return Optional.of(response.getMedia());
        } catch (StatusRuntimeException ex) {
            return Optional.empty();
        }
    }
}
